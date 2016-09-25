package com.reddy.university.api.reports;

import com.reddy.university.domain.models.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by deven on 9/25/2016.
 */
public class StudentReportGenerator {

    /**
     * Build a list of classes being taken by each student
     *
     * @param students returned from domain
     * @return Map where key is student, and value is a list of the classes they are taking
     */
    public static Map<Integer,List<String>> classBreakdown(List<Student> students){
        //simply build and return the map of student and classes.
        return students.stream()
                .collect(Collectors.toMap(Student::getId, student -> student.getUniversityClasses()));
    }

    /**
     *
     * Build a list of how many students take more than one Class
     *
     * @param students returned from domain
     * @return Map where key count of students taking more than 1 class, and value is list of students
     *
     *         returning a map mainly for ease of json construction
     */
    public static Map<Integer,List<Integer>> inMultipleClasses(List<Student> students){
        Map<Integer,List<Integer>> studentMap = new HashMap<Integer, List<Integer>>();

        //filter list of students taking more than 1 class
        List<Student> filteredStudents = students.stream()
                .filter(student -> student.getUniversityClasses().size() > 1)
                .collect(Collectors.toList());

        //build map where key is the size of the list of students, and value is the list of students.
        studentMap.put(filteredStudents.size(), filteredStudents.stream().map(student -> student.getId())
                .collect(Collectors.toList()));

        return studentMap;
    }
}
