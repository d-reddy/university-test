package com.reddy.university.domain;

import com.reddy.university.domain.models.Professor;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public interface IProfessorService {
    List<Professor> get() throws Exception;
}
