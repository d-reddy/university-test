package com.reddy.university.domain.mappers;

import com.reddy.university.domain.models.UniversityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClassMapper {

    /**
     * this is a mapper to transform entities read from the "db repository" to business models for the domain
     *
     * @param entities
     * @return a list of business domain models
     */
    public static List<UniversityClass> toModels(List<com.reddy.university.repository.entities.UniversityClass> entities){
        List<UniversityClass> models = new ArrayList<>();
        entities.forEach(entity -> {
            UniversityClass model = new UniversityClass(entity.getName(), entity.getProfessor().getName());
            entity.getStudents().forEach(student -> model.addStudent(student.getId()));
            models.add(model);
        });
        return models;
    }

}
