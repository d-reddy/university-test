package com.reddy.university.domain.services;

import com.reddy.university.domain.IUniversityClassService;
import com.reddy.university.domain.impl.UniversityClassService;
import com.reddy.university.repository.IUniversityClassRepository;
import com.reddy.university.repository.entities.*;
import com.reddy.university.repository.impl.UniversityClassRepository;
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
public class UniversityClassServiceTest{
    private IUniversityClassRepository universityClassRepositoryMock;
    private IUniversityClassService universityClassService;

    @Before
    public void setup() throws Exception {
        List<UniversityClass> classes = new ArrayList();
        UniversityClass entity = new UniversityClass("Mathematics", new Professor("Jim"));
        entity.addStudent(new Student(23));
        entity.addStudent(new Student(33));
        classes.add(entity);

        universityClassRepositoryMock = mock(UniversityClassRepository.class);
        universityClassService = new UniversityClassService(universityClassRepositoryMock);

        doReturn(classes).when(universityClassRepositoryMock).get();
    }

    @Test
    public void whenGetCalled_hitsUniversityClassRepository1Time() throws Exception {

        universityClassService.get();

        verify(universityClassRepositoryMock,times(1)).get();
    }

    @Test
    public void whenGetCalled_willMapCorrectly() throws Exception {

        List<com.reddy.university.domain.models.UniversityClass> classes = universityClassService.get();

        Assert.assertEquals(classes.size(), 1);
        Assert.assertEquals(classes.get(0).getStudents().size(), 2);
        Assert.assertTrue(classes.get(0).getProfessor().equals("Jim"));
    }
}
