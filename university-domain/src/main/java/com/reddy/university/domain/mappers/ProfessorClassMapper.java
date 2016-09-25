package com.reddy.university.domain.mappers;

import com.reddy.university.domain.models.Professor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class ProfessorClassMapper {

    /**
     * this is a mapper to transform entities read from the "db repository" to business models for the domain
     *
     * @param entities
     * @return a list of business domain models
     */
    public static List<Professor> toModels(List<com.reddy.university.repository.entities.Professor> entities){
        List<Professor> models = new ArrayList<>();
        entities.forEach(entity -> {
            Professor model = new Professor(entity.getName());
            entity.getUniversityClasses().forEach(universityClass -> model.addClass(universityClass.getName()));
            models.add(model);
        });
        return models;
    }
}
