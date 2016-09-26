package com.reddy.university.domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class Student {
    private Integer id;
    private List<String> universityClasses = new ArrayList<>();

    public Student(Integer id){
        this.id = id;
    }

    public List<String> getUniversityClasses() {
        return universityClasses;
    }

    public void addClass(String universityClass) {
        this.universityClasses.add(universityClass);
    }

    public Integer getId() {
        return id;
    }

    public Integer getRegisteredClassCount() {
        return universityClasses.size();
    }
}
