package com.reddy.university.domain.mappers;

import com.reddy.university.domain.impl.ProfessorService;
import com.reddy.university.repository.entities.Professor;
import com.reddy.university.repository.entities.UniversityClass;
import com.reddy.university.repository.impl.ProfessorRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Created by deven on 9/25/2016.
 */
public class ProfessorMapperTest {

    @Test
    public void whenProfessorWithNameAndClass_willMapToDomainProfessorWithNameAndClass() throws Exception {

        List<Professor> professors = new ArrayList<>();

        Professor professor = new Professor("Jim");
        professor.addUniversityClass(new UniversityClass("Mathematics", null));
        professors.add(professor);

        List<com.reddy.university.domain.models.Professor> models = ProfessorClassMapper.toModels(professors);

        Assert.assertEquals(models.size(), 1);
        Assert.assertTrue(models.get(0).getName().equals("Jim"));
        Assert.assertTrue(models.get(0).getUniversityClasses().get(0).equals("Mathematics"));

    }

    @Test(expected=Exception.class)
    public void whenInvalidProfessor_willThrowException() throws Exception {
        ProfessorClassMapper.toModels(null);
    }
}
