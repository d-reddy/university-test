package com.reddy.university.repository.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/23/2016.
 */
public class Student {

    private Integer id;
    private List<UniversityClass> universityClasses = new ArrayList<>();

    public Student(Integer id){
        this.id = id;
    }

    public List<UniversityClass> getUniversityClasses() {
        return universityClasses;
    }

    public void addUniversityClass(UniversityClass universityClass){
        this.universityClasses.add(universityClass);
    }

    public Integer getId() {
        return id;
    }
}
