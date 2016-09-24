package com.reddy.university.repository;

import com.reddy.university.repository.entities.UniversityClass;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public interface IUniversityClassRepository {
    List<UniversityClass> get() throws Exception;
}
