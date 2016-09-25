package com.reddy.university.repository;

import com.reddy.university.repository.entities.*;
import com.reddy.university.repository.impl.Database;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by deven on 9/23/2016.
 */
public class DatabaseTest {

    @Test
    public void when1Class1Prof2Students_buildsValidUniversityClassEntityRelationshipTree() throws Exception{

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("1class1prof2students.csv").getFile());

        Database db = new Database(file);

        List<UniversityClass> classes = db.getUniversityClasses();
        List<Student> students = db.getStudents();
        List<Professor> professors = db.getProfessors();

        //assert entities exist
        Assert.assertEquals(db.getUniversityClasses().size(),1);
        Assert.assertEquals(db.getUniversityClasses().get(0).getStudents().size(),2);
        Assert.assertNotNull(db.getUniversityClasses().get(0).getProfessor());

        //assert entity relationships
        Assert.assertTrue(db.getUniversityClasses().get(0).getProfessor().getName().equals("Joseph"));
        Assert.assertEquals(db.getUniversityClasses().get(0).getStudents().size(),2);
        Assert.assertEquals(db.getUniversityClasses().get(0).getStudents().get(0).getUniversityClasses().size(),1);
        Assert.assertEquals(db.getUniversityClasses().get(0).getStudents().get(1).getUniversityClasses().size(),1);
        Assert.assertTrue(db.getUniversityClasses().get(0).getStudents().get(0).getId().equals(1234));
        Assert.assertTrue(db.getUniversityClasses().get(0).getStudents().get(1).getId().equals(3455));
        Assert.assertTrue(db.getUniversityClasses().get(0).getStudents().get(0).getUniversityClasses().get(0).getName().equals("Chemistry"));
        Assert.assertTrue(db.getUniversityClasses().get(0).getStudents().get(1).getUniversityClasses().get(0).getName().equals("Chemistry"));

    }

    @Test(expected=Exception.class)
    public void whenDataIntegrityViolationInCsv_throwsException() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("1class0prof0student.csv").getFile());

        Database db = new Database(file);

        List<UniversityClass> classes = db.getUniversityClasses();
    }


    @Test(expected=FileNotFoundException.class)
    public void whenInputFileNotFound_throwsException() throws Exception {
        Database db = new Database("/does/not/exist");
        List<UniversityClass> classes = db.getUniversityClasses();
    }
}
