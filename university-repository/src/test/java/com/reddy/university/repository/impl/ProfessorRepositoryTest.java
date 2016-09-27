package com.reddy.university.repository.impl;

import com.reddy.university.repository.IDatabase;
import com.reddy.university.repository.IProfessorRepository;
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
public class ProfessorRepositoryTest {
    private IDatabase databaseMock;
    private IProfessorRepository professorRepository;

    @Before
    public void setup() throws Exception {
        List<Professor> professors = new ArrayList<>();

        Professor professor = new Professor("Reddy");
        UniversityClass universityClass = new UniversityClass("Mathematics", professor);
        professor.addUniversityClass(universityClass);
        professors.add(professor);

        databaseMock = mock(Database.class);
        professorRepository = new ProfessorRepository(databaseMock);

        doReturn(professors).when(databaseMock).getProfessors();
    }

    @Test
    public void whenGetProfessorsCalled_hitsDatabaseMock1Time() throws Exception {

        professorRepository.get();
        verify(databaseMock,times(1)).getProfessors();
    }
}
