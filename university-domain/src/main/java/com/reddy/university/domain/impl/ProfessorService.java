package com.reddy.university.domain.impl;

import com.google.inject.Inject;
import com.reddy.university.domain.IProfessorService;
import com.reddy.university.domain.mappers.ProfessorClassMapper;
import com.reddy.university.domain.models.Professor;
import com.reddy.university.repository.IProfessorRepository;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class ProfessorService implements IProfessorService {
    private IProfessorRepository professorRepository;

    @Inject
    public ProfessorService(IProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> get() throws Exception {
        return ProfessorClassMapper.toModels(professorRepository.get());
    }

}
