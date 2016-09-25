package com.reddy.university.repository.impl;

import com.google.inject.Inject;
import com.reddy.university.repository.IDatabase;
import com.reddy.university.repository.IUniversityClassRepository;
import com.reddy.university.repository.entities.UniversityClass;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClassRepository implements IUniversityClassRepository{
    private IDatabase database;

    @Inject
    public UniversityClassRepository(IDatabase database){
        this.database = database;
    }

    @Override
    public List<UniversityClass> get() throws Exception {
        return this.database.getUniversityClasses();
    }

}
