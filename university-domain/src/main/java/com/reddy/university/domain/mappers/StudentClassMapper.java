package com.reddy.university.domain.mappers;

import com.reddy.university.domain.models.Student;
import com.reddy.university.domain.models.UniversityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class StudentClassMapper {
    public List<Student> toModels(List<com.reddy.university.repository.entities.Student> entities){
        List<Student> models = new ArrayList<>();
        entities.forEach(entity -> {
            Student model = new Student(entity.getId());
            entity.getUniversityClasses().forEach(universityClass -> model.addClass(universityClass.getName()));
            models.add(model);
        });
        return models;
    }
}
