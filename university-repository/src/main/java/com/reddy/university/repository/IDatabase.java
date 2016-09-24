package com.reddy.university.repository;

import com.reddy.university.repository.entities.Professor;
import com.reddy.university.repository.entities.Student;
import com.reddy.university.repository.entities.UniversityClass;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public interface IDatabase {
    List<Student> getStudents() throws Exception;

    List<Professor> getProfessors() throws Exception;

    List<UniversityClass> getUniversityClasses() throws Exception;
}
