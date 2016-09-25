package com.reddy.university.domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClass {
    private List<Integer> students = new ArrayList<>();
    private String name;
    private String professor;

    public UniversityClass(String name, String professor){
        this.name = name;
        this.professor = professor;
    }

    public List<Integer> getStudents() {
        return students;
    }

    public void addStudent(Integer studentId) {
        this.students.add(studentId);
    }

    public String getName() {
        return name;
    }

    public String getProfessor() {
        return professor;
    }

    public Integer getRegisteredStudents() {
        return students.size();
    }
}