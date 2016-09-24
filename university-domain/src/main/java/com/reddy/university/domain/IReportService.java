package com.reddy.university.domain;

import com.reddy.university.domain.models.Student;
import com.reddy.university.domain.models.UniversityClassSummary;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public interface IReportService {

    List<UniversityClassSummary> getClassesTaught() throws Exception;

    //List<Student> getStudentClasses() throws Exception;

}
