package com.reddy.university.repository.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/23/2016.
 */
public class UniversityClass {
    private String name;
    private Professor professor;
    private List<Student> students = new ArrayList<>();

    public UniversityClass(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
