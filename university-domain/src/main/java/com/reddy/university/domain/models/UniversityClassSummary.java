package com.reddy.university.domain.models;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClassSummary {
    public String name;
    public String professor;

    public UniversityClassSummary(String name, String professor){
        this.name = name;
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public String getProfessor() {
        return professor;
    }

}
