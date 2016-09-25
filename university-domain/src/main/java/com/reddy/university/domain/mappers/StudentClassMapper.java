package com.reddy.university.domain.mappers;

import com.reddy.university.domain.models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class StudentClassMapper {

    /**
     * this is a mapper to transform entities read from the "db repository" to business models for the domain
     *
     * @param entities
     * @return a list of business domain models
     */
    public static List<Student> toModels(List<com.reddy.university.repository.entities.Student> entities){
        List<Student> models = new ArrayList<>();
        entities.forEach(entity -> {
            Student model = new Student(entity.getId());
            entity.getUniversityClasses().forEach(universityClass -> model.addClass(universityClass.getName()));
            models.add(model);
        });
        return models;
    }
}
