package com.reddy.university.api.reports;

import com.reddy.university.domain.models.UniversityClass;
import org.apache.commons.collections.map.MultiValueMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by deven on 9/25/2016.
 */
public class UniversityClassReportGenerator {

    /**
     * Build list of how many students are registered for each class
     *
     * @param universityClasses from domain
     * @return Map where key is the class, and value is the number of students registered
     */
    public static Map<String, Integer> universityClassBreakdown(List<UniversityClass> universityClasses){
        //return a map grouping by the classname, and summing the registered students across the grouping
        return universityClasses.stream()
                .collect(Collectors
                        .groupingBy(UniversityClass::getName,
                                Collectors.summingInt(UniversityClass::getRegisteredStudentCount)));
    }

    /**
     * Build list of class sections taught.
     *
     * @param universityClasses from domain
     * @return MultiValueMap, which is pretty much a tuple of string, string, used to build the Class/Professor
     *         pairs.
     */
    public static MultiValueMap universityClasses(List<UniversityClass> universityClasses) {

        MultiValueMap availableClasses = new MultiValueMap();

        //build the map of class/professor pairs.
        universityClasses.forEach(universityClass -> {
            availableClasses.put(universityClass.getName(), universityClass.getProfessor());
        });

        return availableClasses;
    }
}
