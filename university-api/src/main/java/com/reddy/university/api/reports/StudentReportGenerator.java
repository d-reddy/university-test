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
    public static Map<Integer,List<String>> classBreakdown(List<Student> students){
        return students.stream().collect(Collectors.toMap(Student::getId, student -> student.getUniversityClasses()));
    }

    public static Map<Integer,List<Integer>> inMultipleClasses(List<Student> students){
        Map<Integer,List<Integer>> studentMap = new HashMap<Integer, List<Integer>>();

        List<Student> filteredStudents = students.stream().filter(student -> student.getUniversityClasses().size() > 1).collect(Collectors.toList());
        studentMap.put(filteredStudents.size(), filteredStudents.stream().map(student -> student.getId()).collect(Collectors.toList()));

        return studentMap;
    }
}
