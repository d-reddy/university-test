package com.reddy.university.domain.mappers;

import com.reddy.university.domain.models.UniversityClass;
import com.reddy.university.domain.models.UniversityClassSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClassMapper {
    public List<UniversityClass> toModels(List<com.reddy.university.repository.entities.UniversityClass> entities){
        List<UniversityClass> models = new ArrayList<>();
        entities.forEach(entity -> {
            UniversityClass model = new UniversityClass(entity.getName(), entity.getProfessor().getName());
            entity.getStudents().forEach(student -> model.addStudent(student.getId()));
            models.add(model);
        });
        return models;
    }

    public List<UniversityClassSummary> toModelSummaries(List<com.reddy.university.repository.entities.UniversityClass> entities){
        List<UniversityClassSummary> models = new ArrayList<>();
        entities.forEach(entity -> {
            UniversityClassSummary model = new UniversityClassSummary(entity.getName(), entity.getProfessor().getName());
            models.add(model);
        });
        return models;
    }
}