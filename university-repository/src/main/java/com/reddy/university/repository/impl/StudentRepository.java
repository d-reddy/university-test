package com.reddy.university.repository.impl;

import com.google.inject.Inject;
import com.reddy.university.repository.IDatabase;
import com.reddy.university.repository.IStudentRepository;
import com.reddy.university.repository.entities.Student;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class StudentRepository implements IStudentRepository{
    private IDatabase database;

    @Inject
    public StudentRepository(IDatabase database){
        this.database = database;
    }

    @Override
    public List<Student> get() throws Exception {
        return this.database.getStudents();
    }
}
