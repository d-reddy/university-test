package com.reddy.university.domain.impl;

import com.google.inject.Inject;
import com.reddy.university.domain.IReportService;
import com.reddy.university.domain.IUniversityClassService;
import com.reddy.university.domain.mappers.UniversityClassMapper;
import com.reddy.university.domain.models.UniversityClass;
import com.reddy.university.domain.models.UniversityClassSummary;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class ReportService implements IReportService {

    private IUniversityClassService universityClassService;

    @Inject
    public ReportService(IUniversityClassService universityClassService){
        this.universityClassService = universityClassService;
    }

    @Override
    public List<UniversityClassSummary> getClassesTaught() throws Exception {
        return universityClassService.getSummaries();
    }
}
