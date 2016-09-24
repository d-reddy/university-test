package com.reddy.university.domain.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClass extends UniversityClassSummary {
    private List<Integer> students = new ArrayList<>();

    public UniversityClass(String name, String professor){
        super(name, professor);
    }

    public List<Integer> getStudents() {
        return students;
    }

    public void addStudent(Integer studentId) {
        this.students.add(studentId);
    }
}