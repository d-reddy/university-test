package com.reddy.university.api.resources;

import com.google.inject.Inject;
import com.reddy.university.api.models.Report;
import com.reddy.university.api.reports.*;
import com.reddy.university.domain.*;
import com.reddy.university.domain.models.*;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

/**
 * Created by deven on 9/24/2016.
 */
@Path("/report")
public class ReportingResource {

    final static Logger logger = Logger.getLogger(ReportingResource.class);

    private IUniversityClassService universityClassService;
    private IStudentService studentService;
    private IProfessorService professorService;

    @Inject
    public ReportingResource(IUniversityClassService universityClassService, IStudentService studentService,
                             IProfessorService professorService) {
        this.universityClassService = universityClassService;
        this.studentService = studentService;
        this.professorService = professorService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Report index() throws Exception {
        Report report = new Report();

        try {

            UUID id = java.util.UUID.randomUUID();
            logger.info("starting request " + id);

            //university class reports
            List<UniversityClass> universityClasses = this.universityClassService.get();

            report.universityClassReport = UniversityClassReportGenerator.universityClasses(universityClasses);
            report.universityClassBreakdown = UniversityClassReportGenerator.universityClassBreakdown(universityClasses);

            //student reports
            List<Student> students = this.studentService.get();
            report.studentClassReport = StudentReportGenerator.classBreakdown(students);
            report.studentClassCountReport = StudentReportGenerator.inMultipleClasses(students);

            //professor reports
            List<Professor> professors = this.professorService.get();
            report.professorClassCountReport = ProfessorReportGenerator.teachMultipleClasses(professors);
            report.professorWith2OrMoreOfSameStudent = ProfessorReportGenerator.withTwoOrMoreCommonStudentsInEachClass(universityClasses);

            logger.info("completing request " + id);

        } catch(Exception e){
            throw e;
        }

        return report;
    }
}