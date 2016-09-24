package com.reddy.university.api.resources;

import com.google.inject.Inject;
import com.reddy.university.api.mappers.StudentClassMapper;
import com.reddy.university.api.mappers.UniversityClassMapper;
import com.reddy.university.api.models.report.Report;
import com.reddy.university.api.models.report.StudentClassReport;
import com.reddy.university.api.models.report.UniversityClassReport;
import com.reddy.university.api.models.report.UniversityClassSummary;
import com.reddy.university.domain.IStudentService;
import com.reddy.university.domain.IUniversityClassService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Created by deven on 9/24/2016.
 */
@Path("/report")
public class ReportingResource {
    private IUniversityClassService universityClassService;
    private IStudentService studentService;

    @Inject
    public ReportingResource(IUniversityClassService universityClassService, IStudentService studentService) {
        this.universityClassService = universityClassService;
        this.studentService = studentService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Report index() {
        Report report = new Report();

        try {
            UniversityClassReport universityClassReport = new UniversityClassReport();
            UniversityClassMapper universityClassMapper = new UniversityClassMapper();
            universityClassReport.classSummaries = universityClassMapper.toModelSummaries(this.universityClassService.get()) ;
            report.universityClassReport = universityClassReport;

            StudentClassReport studentClassReport = new StudentClassReport();
            StudentClassMapper studentClassMapper = new StudentClassMapper();
            studentClassReport.studentSummaries = studentClassMapper.toModelSummaries(this.studentService.get());
            report.studentClassReport = studentClassReport;

            Map<String,Integer> classBreakdown = universityClassService.getStudentBreakdown();

        } catch(Exception e){

        }

        return report;
    }

}
