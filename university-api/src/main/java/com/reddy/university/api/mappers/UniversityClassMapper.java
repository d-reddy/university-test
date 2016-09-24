package com.reddy.university.api.mappers;


import com.reddy.university.api.models.report.UniversityClassStudentSummary;
import com.reddy.university.api.models.report.UniversityClassSummary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClassMapper {
    public List<UniversityClassSummary> toModelSummaries(List<com.reddy.university.domain.models.UniversityClass> domainModels){
        List<UniversityClassSummary> models = new ArrayList<>();
        domainModels.forEach(domainModel -> {
            UniversityClassSummary model = new UniversityClassSummary(domainModel.getName(), domainModel.getProfessor());
            models.add(model);
        });
        return models;
    }

    public List<UniversityClassStudentSummary> toModelSummariesWithStudents(List<com.reddy.university.domain.models.UniversityClass> domainModels){
        List<UniversityClassStudentSummary> models = new ArrayList<>();
        domainModels.forEach(domainModel -> {
            UniversityClassStudentSummary model = new UniversityClassStudentSummary(domainModel.getName(), domainModel.getProfessor());
            domainModel.getStudents().forEach(student -> model.students.add(student));
            models.add(model);
        });
        return models;
    }
}
