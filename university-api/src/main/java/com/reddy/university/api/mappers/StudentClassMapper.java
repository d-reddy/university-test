package com.reddy.university.api.mappers;


import com.reddy.university.api.models.report.StudentClassSummary;
import com.reddy.university.api.models.report.UniversityClassSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class StudentClassMapper {
    public List<StudentClassSummary> toModelSummaries(List<com.reddy.university.domain.models.Student> domainModels){
        List<StudentClassSummary> models = new ArrayList<>();
        domainModels.forEach(domainModel -> {
            StudentClassSummary model = new StudentClassSummary(domainModel.getId());
            domainModel.getUniversityClasses().forEach(universityClass -> model.classes.add(universityClass));
            models.add(model);
        });
        return models;
    }
}
