package com.reddy.university.api.reports;


import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.reddy.university.domain.models.Professor;
import com.reddy.university.domain.models.Student;
import com.reddy.university.domain.models.UniversityClass;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by deven on 9/25/2016.
 */
public class ProfessorReportGenerator {
    public static Map<Integer,List<String>> teachMultipleClasses(List<Professor> professors){
        Map<Integer,List<String>> professorMap = new HashMap<Integer, List<String>>();

        List<Professor> filteredProfessors = professors.stream().filter(professor -> professor.getUniversityClasses().size() > 1).collect(Collectors.toList());
        professorMap.put(filteredProfessors.size(), filteredProfessors.stream().map(professor -> professor.getName()).collect(Collectors.toList()));

        return professorMap;
    }

    public static Map<String, List<String>> withTwoOrMoreOfTheSameStudentInEachClassTheyTeach(List<UniversityClass> universityClasses){

        Map<String, List<String>> professorMatches = new HashMap<String, List<String>>();
        Map<String, List<UniversityClass>> professorBreakdown = universityClasses.stream().collect(Collectors.groupingBy(z -> z.getProfessor()));

        professorBreakdown.forEach((professor, listClasses) ->{
            if (listClasses.size() >= 2){
                List<List<Integer>> studentLists = listClasses.stream().map(universityClass -> universityClass.getStudents()).collect(Collectors.toList());

                Set<Integer> intersection = new HashSet<Integer>(studentLists.get(0));
                for (List<Integer> studentList : studentLists.subList(1, studentLists.size())) {
                    intersection = Sets.intersection(intersection, new HashSet<Integer>(studentList));
                }
                List<Integer> students = Lists.newArrayList(intersection);

                if (students.size() >= 2){
                    List<String> data = listClasses.stream().map(universityClass -> universityClass.getName())
                            .collect(Collectors.toList());
                    data.addAll(students.stream().map(student -> student.toString()).collect(Collectors.toList()));
                    professorMatches.put(professor, data);
                }
            }
        } );

        return professorMatches;
    }

}
