package com.reddy.university.repository;

import com.reddy.university.repository.entities.Student;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public interface IStudentRepository {
    List<Student> get() throws Exception;
}
