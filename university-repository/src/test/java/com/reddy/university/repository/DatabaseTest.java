package com.reddy.university.repository;

import com.reddy.university.repository.entities.Professor;
import com.reddy.university.repository.entities.Student;
import com.reddy.university.repository.entities.UniversityClass;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by deven on 9/23/2016.
 */
public class DatabaseTest {

    @Test
    public void whenTwoDistinctClassRecords_ReturnsTwoClasses() throws Exception{
        Database db = new Database("resources/test.csv");

        List<UniversityClass> classes = db.getClasses();
        List<Student> students = db.getStudents();
        List<Professor> professors = db.getProfessors();

        Assert.assertEquals(db.getClasses().size(),11);
    }
}
