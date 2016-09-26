package com.reddy.university.repository;

import com.reddy.university.repository.entities.Professor;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public interface IProfessorRepository {
    List<Professor> get() throws Exception;
}
