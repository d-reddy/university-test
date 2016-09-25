package com.reddy.university.api.resources;

import com.google.inject.Inject;
import com.reddy.university.api.models.Report;
import com.reddy.university.api.reports.ProfessorReportGenerator;
import com.reddy.university.api.reports.StudentReportGenerator;
import com.reddy.university.api.reports.UniversityClassReportGenerator;
import com.reddy.university.domain.IProfessorService;
import com.reddy.university.domain.IStudentService;
import com.reddy.university.domain.IUniversityClassService;
import com.reddy.university.domain.models.Professor;
import com.reddy.university.domain.models.Student;
import com.reddy.university.domain.models.UniversityClass;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
@Path("/report")
public class ReportingResource {
    private IUniversityClassService universityClassService;
    private IStudentService studentService;
    private IProfessorService professorService;

    @Inject
    public ReportingResource(IUniversityClassService universityClassService, IStudentService studentService, IProfessorService professorService) {
        this.universityClassService = universityClassService;
        this.studentService = studentService;
        this.professorService = professorService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Report index() {
        Report report = new Report();

        try {

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
            report.professorWith2OrMoreOfSameStudent = ProfessorReportGenerator.withTwoOrMoreOfTheSameStudentInEachClassTheyTeach(universityClasses);

        } catch(Exception e){

        }

        return report;
    }

}
