package com.reddy.university.domain.mappers;

import com.reddy.university.repository.entities.Professor;
import com.reddy.university.repository.entities.Student;
import com.reddy.university.repository.entities.UniversityClass;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/25/2016.
 */
public class StudentMapperTest {

    @Test
    public void whenProfessorWithNameAndClass_willMapToDomainProfessorWithNameAndClass() throws Exception {

        List<Student> students = new ArrayList<>();

        Student student = new Student(23);
        UniversityClass universityClass = new UniversityClass("Mathematics", new Professor("Jim"));
        universityClass.addStudent(student);
        student.addUniversityClass(universityClass);
        students.add(student);

        List<com.reddy.university.domain.models.Student> models = StudentClassMapper.toModels(students);

        Assert.assertEquals(models.size(), 1);
        Assert.assertEquals(models.get(0).getUniversityClasses().size(), 1);
        Assert.assertTrue(models.get(0).getUniversityClasses().get(0).equals("Mathematics"));

    }

    @Test(expected=Exception.class)
    public void whenInvalidStudent_willThrowException() throws Exception {
        StudentClassMapper.toModels(null);
    }
}
