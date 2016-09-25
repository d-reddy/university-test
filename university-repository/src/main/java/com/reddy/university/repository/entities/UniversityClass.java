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

    public UniversityClass(String name, Professor professor){
        this.name = name;
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }
}
