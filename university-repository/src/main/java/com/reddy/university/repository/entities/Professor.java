package com.reddy.university.repository.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/23/2016.
 */
public class Professor {

    private String name;
    private List<UniversityClass> universityClasses = new ArrayList<>();

    public Professor(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<UniversityClass> getUniversityClasses() {
        return universityClasses;
    }

    public void addUniversityClass(UniversityClass universityClass) {
        this.universityClasses.add(universityClass);
    }
}
