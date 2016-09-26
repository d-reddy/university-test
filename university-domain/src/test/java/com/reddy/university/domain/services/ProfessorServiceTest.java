package com.reddy.university.domain.services;

import com.reddy.university.domain.IProfessorService;
import com.reddy.university.domain.impl.ProfessorService;
import com.reddy.university.repository.IProfessorRepository;
import com.reddy.university.repository.entities.*;
import com.reddy.university.repository.impl.ProfessorRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by deven on 9/24/2016.
 *
 *
 * The tests for this domain are fairly light, mainly because this domain mainly functions as a pass through
 * to the repository.
 *
 * What the tests are trying to do here are verify the mapper to the domian objects appear to work.
 *
 */
public class ProfessorServiceTest {
    private IProfessorRepository professorRepositoryMock;
    private IProfessorService professorService;

    @Before
    public void setup() throws Exception {
        List<Professor> professors = new ArrayList<>();

        Professor professor = new Professor("Reddy");
        UniversityClass universityClass = new UniversityClass("Mathematics", professor);
        professor.addUniversityClass(universityClass);
        professors.add(professor);

        professorRepositoryMock = mock(ProfessorRepository.class);
        professorService = new ProfessorService(professorRepositoryMock);

        doReturn(professors).when(professorRepositoryMock).get();
    }

    @Test
    public void whenGetCalled_hitsStudentRepository1Time() throws Exception {

        professorService.get();
        verify(professorRepositoryMock,times(1)).get();
    }

    @Test
    public void whenGetCalled_willMapCorrectly() throws Exception {

        List<com.reddy.university.domain.models.Professor> professors = professorService.get();

        Assert.assertEquals(professors.size(), 1);
        Assert.assertEquals(professors.get(0).getUniversityClasses().size(), 1);
        Assert.assertTrue(professors.get(0).getUniversityClasses().get(0).equals("Mathematics"));
    }
}
