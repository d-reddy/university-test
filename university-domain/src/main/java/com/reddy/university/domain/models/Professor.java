package com.reddy.university.domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class Professor {
    private String name;
    private List<String> universityClasses = new ArrayList<>();

    public Professor(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getUniversityClasses() {
        return universityClasses;
    }
}
