package com.reddy.university.repository.impl;

import com.reddy.university.repository.IDatabase;
import com.reddy.university.repository.IUniversityClassRepository;
import com.reddy.university.repository.entities.Professor;
import com.reddy.university.repository.entities.UniversityClass;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by deven on 9/25/2016.
 */
public class UniversityClassRepositoryTest {
    private IDatabase databaseMock;
    private IUniversityClassRepository universityClassRepository;

    @Before
    public void setup() throws Exception {
        List<UniversityClass> universityClasses = new ArrayList<>();

        UniversityClass universityClass = new UniversityClass("Mathematics",new Professor("Smith"));
        universityClasses.add(universityClass);

        databaseMock = mock(Database.class);
        universityClassRepository = new UniversityClassRepository(databaseMock);

        doReturn(universityClasses).when(databaseMock).getUniversityClasses();
    }

    @Test
    public void whenGetUniversityClassCalled_hitsDatabaseMock1Time() throws Exception {

        universityClassRepository.get();
        verify(databaseMock,times(1)).getUniversityClasses();
    }
}
