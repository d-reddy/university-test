package com.reddy.university.api.reports;

import com.reddy.university.domain.models.Student;
import com.reddy.university.domain.models.UniversityClass;
import org.apache.commons.collections.map.MultiValueMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by deven on 9/25/2016.
 */
public class UniversityClassReportGenerator {
    public static Map<String, Integer> universityClassBreakdown(List<UniversityClass> universityClasses){
        return universityClasses.stream().collect(Collectors.groupingBy(UniversityClass::getName, Collectors.summingInt(UniversityClass::getRegisteredStudentCount)));
    }

    public static MultiValueMap universityClasses(List<UniversityClass> universityClasses) {

        MultiValueMap availableClasses = new MultiValueMap();
        universityClasses.forEach(universityClass -> {
            availableClasses.put(universityClass.getName(), universityClass.getProfessor());
        });

        return availableClasses;
    }
}
