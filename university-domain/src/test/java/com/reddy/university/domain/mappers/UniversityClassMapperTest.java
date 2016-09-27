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
public class UniversityClassMapperTest {
    @Test
    public void whenUniversityClassWithNameAndProfessor_willMapToDomainUniversityClassWithNameAndProfessor() throws Exception {

        List<UniversityClass> classes = new ArrayList();
        UniversityClass entity = new UniversityClass("Mathematics", new Professor("Jim"));
        entity.addStudent(new Student(23));
        entity.addStudent(new Student(33));
        classes.add(entity);

        List<com.reddy.university.domain.models.UniversityClass> models = UniversityClassMapper.toModels(classes);

        Assert.assertEquals(models.size(), 1);
        Assert.assertEquals(models.get(0).getStudents().size(), 2);
        Assert.assertTrue(models.get(0).getProfessor().equals("Jim"));

    }

    @Test(expected=Exception.class)
    public void whenInvalidUniversityClass_willThrowException() throws Exception {
        UniversityClassMapper.toModels(null);
    }
}
