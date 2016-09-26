package com.reddy.university.domain.services;

import com.reddy.university.domain.IStudentService;
import com.reddy.university.domain.impl.StudentService;
import com.reddy.university.repository.IStudentRepository;
import com.reddy.university.repository.entities.*;
import com.reddy.university.repository.impl.StudentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by deven on 9/24/2016.
 *
 * The tests for this domain are fairly light, mainly because this domain mainly functions as a pass through
 * to the repository.
 *
 * What the tests are trying to do here are verify the mapper to the domian objects appear to work.
 *
 */
public class StudentClassServiceTest {
    private IStudentRepository studentRepositoryMock;
    private IStudentService studentService;

    @Before
    public void setup() throws Exception {
        List<Student> students = new ArrayList<>();

        Student student = new Student(23);
        UniversityClass universityClass = new UniversityClass("Mathematics", new Professor("Jim"));
        universityClass.addStudent(student);
        student.addUniversityClass(universityClass);
        students.add(student);

        studentRepositoryMock = mock(StudentRepository.class);
        studentService = new StudentService(studentRepositoryMock);

        doReturn(students).when(studentRepositoryMock).get();
    }

    @Test
    public void whenGetCalled_hitsStudentRepository1Time() throws Exception {

        studentService.get();
        verify(studentRepositoryMock,times(1)).get();
    }

    @Test
    public void whenGetCalled_willMapCorrectly() throws Exception {

        List<com.reddy.university.domain.models.Student> students = studentService.get();

        Assert.assertEquals(students.size(), 1);
        Assert.assertEquals(students.get(0).getUniversityClasses().size(), 1);
        Assert.assertTrue(students.get(0).getUniversityClasses().get(0).equals("Mathematics"));
    }
}
