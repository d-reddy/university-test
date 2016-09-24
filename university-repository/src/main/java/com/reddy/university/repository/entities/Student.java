package com.reddy.university.repository.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/23/2016.
 */
public class Student {

    private Integer id;
    private List<UniversityClass> universityClasses = new ArrayList<>();

    public List<UniversityClass> getUniversityClasses() {
        return universityClasses;
    }

    public void setUniversityClasses(List<UniversityClass> universityClasses) {
        this.universityClasses = universityClasses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
