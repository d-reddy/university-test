package com.reddy.university.api.reports;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.reddy.university.domain.models.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by deven on 9/25/2016.
 */
public class ProfessorReportGenerator {

    /**
     *
     * Builds a list of professors that teach multiple university classes
     *
     * @param professors returned from the domain model
     * @return Map where the key is the count of professors teaching multiple classes, and the value is the list of
     *         professors.
     *
     *         returning a map mainly for ease of json construction
     */
    public static Map<Integer,List<String>> teachMultipleClasses(List<Professor> professors){
        Map<Integer,List<String>> professorMap = new HashMap<Integer, List<String>>();

        //get all professors who teach more than 1 class
        List<Professor> filteredProfessors = professors.stream()
                .filter(professor -> professor.getUniversityClasses().size() > 1)
                .collect(Collectors.toList());

        //build map result to return
        professorMap.put(filteredProfessors.size(), filteredProfessors.stream()
                .map(professor -> professor.getName())
                .collect(Collectors.toList()));

        return professorMap;
    }

    /**
     *
     * Builds a list of professors with at least 2 classes with 2 or more of the same students
     *
     * From requirements:
     * The list of professors that teach at least two different classes that have two or more of the same students
     * in each class they teach.  For each professor that meets the criterion, list his/her classes and the
     * common students.
     *
     * @param universityClasses returned from the domain model
     * @return Map where the key is the professor meeting the criteria and the value is a string concatenation
     *         of the classes and students
     */
    public static Map<String, List<String>> withTwoOrMoreCommonStudentsInEachClass(List<UniversityClass> universityClasses){

        Map<String, List<String>> professorMatches = new HashMap<String, List<String>>();

        //only pull the professors and their classes, for professors that teach 2 or more classes
        Map<String, List<UniversityClass>> professorBreakdown = universityClasses.stream()
                .collect(Collectors.groupingBy(z -> z.getProfessor())).entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 2)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        //for each one of the professors, see if they meet the criteria, and if so, add them
        professorBreakdown.forEach((professor, listClasses) ->{

            //get a list of the list of students for each class the professor teaches
            List<List<Integer>> studentLists = listClasses.stream()
                    .map(universityClass -> universityClass.getStudents())
                    .collect(Collectors.toList());

            //compute the intersection of those lists, because we only care about students that exist in each
            //of the professors classes based on the requirements. ie if a professor teaches 3 classes, and
            //has 2 students that both attend only 2 of his 3 classes, this would NOT meet the requirement.  The
            //same 2 students would need to attend ALL 3 classes to qualify.
            Set<Integer> intersection = new HashSet<Integer>(studentLists.get(0));
            for (List<Integer> studentList : studentLists.subList(1, studentLists.size())) {
                intersection = Sets.intersection(intersection, new HashSet<Integer>(studentList));
            }
            List<Integer> students = Lists.newArrayList(intersection);

            //the professor only meets the criteria if there are 2 or more of the same students that
            //attend each of his classes. ie if the professor teaches 2 classes and there is only 1 common student
            //in both, then the professor does not qualify, there has to be an intersection of >2.
            if (students.size() >= 2){
                List<String> data = listClasses.stream().map(universityClass -> universityClass.getName())
                        .collect(Collectors.toList());
                data.addAll(students.stream().map(student -> student.toString()).collect(Collectors.toList()));
                professorMatches.put(professor, data);
            }
        } );

        return professorMatches;
    }

}
