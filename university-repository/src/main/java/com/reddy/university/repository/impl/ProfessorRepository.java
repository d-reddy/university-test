package com.reddy.university.repository.impl;

import com.google.inject.Inject;
import com.reddy.university.repository.IDatabase;
import com.reddy.university.repository.IProfessorRepository;
import com.reddy.university.repository.entities.Professor;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class ProfessorRepository implements IProfessorRepository{
    private IDatabase database;

    @Inject
    public ProfessorRepository(IDatabase database){
        this.database = database;
    }

    @Override
    public List<Professor> get() throws Exception{
        return database.getProfessors();
    }

}
