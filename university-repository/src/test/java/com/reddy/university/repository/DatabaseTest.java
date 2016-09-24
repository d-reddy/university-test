package com.reddy.university.repository;

import com.reddy.university.repository.entities.Professor;
import com.reddy.university.repository.entities.Student;
import com.reddy.university.repository.entities.UniversityClass;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by deven on 9/23/2016.
 */
public class DatabaseTest {

    @Test
    public void when1Class1Prof2Students_buildsValidEntityUniversityClassRelationshipTree() throws Exception{

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("1class1prof2students.csv").getFile());

        Database db = new Database(file);

        List<UniversityClass> classes = db.getClasses();
        List<Student> students = db.getStudents();
        List<Professor> professors = db.getProfessors();

        //assert entities exist
        Assert.assertEquals(db.getClasses().size(),1);
        Assert.assertEquals(db.getStudents().size(),2);
        Assert.assertEquals(db.getProfessors().size(),1);

        //assert entity relationships
        Assert.assertTrue(db.getClasses().get(0).getProfessor().getName().equals("Joseph"));
        Assert.assertEquals(db.getClasses().get(0).getStudents().size(),2);
        Assert.assertEquals(db.getClasses().get(0).getStudents().get(0).getUniversityClasses().size(),1);
        Assert.assertEquals(db.getClasses().get(0).getStudents().get(1).getUniversityClasses().size(),1);
        Assert.assertTrue(db.getClasses().get(0).getStudents().get(0).getId().equals(1234));
        Assert.assertTrue(db.getClasses().get(0).getStudents().get(1).getId().equals(3455));
        Assert.assertTrue(db.getClasses().get(0).getStudents().get(0).getUniversityClasses().get(0).getName().equals("Chemistry"));
        Assert.assertTrue(db.getClasses().get(0).getStudents().get(1).getUniversityClasses().get(0).getName().equals("Chemistry"));

    }
}
