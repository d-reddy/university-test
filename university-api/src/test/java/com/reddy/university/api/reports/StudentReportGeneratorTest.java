package com.reddy.university.api.reports;

import com.reddy.university.domain.models.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by deven on 9/25/2016.
 */
public class StudentReportGeneratorTest {

    @Test
    public void whenHas1Student3ClassesAnd1Student2Classes_returnsCorrectMap() throws Exception{

        List<Student> studentList = new ArrayList<>();

        Student student1 = new Student(21);
        student1.addClass("Mathematics");
        student1.addClass("History");
        student1.addClass("CS");
        studentList.add(student1);

        Student student2 = new Student(22);
        student2.addClass("Gymnastics");
        student2.addClass("Psychology");
        studentList.add(student2);

        Map<Integer,List<String>> results = StudentReportGenerator.classBreakdown(studentList);

        //assert student class breakdown
        Assert.assertTrue(results.size() ==  2);
        Assert.assertTrue(results.get(21).size() == 3);
        Assert.assertTrue(results.get(22).size() == 2);
    }

    @Test
    public void whenHasStudentsWithMultipleClasses_returnsOnlyStudentsWithMultipleClasses() throws Exception{
        List<Student> studentList = new ArrayList<>();

        Student student1 = new Student(21);
        student1.addClass("Mathematics");
        student1.addClass("History");
        student1.addClass("CS");
        studentList.add(student1);

        Student student2 = new Student(22);
        student2.addClass("Gymnastics");
        student2.addClass("Psychology");
        studentList.add(student2);

        Student student3 = new Student(23);
        student3.addClass("Gymnastics");
        studentList.add(student3);

        Map<Integer,List<Integer>> results = StudentReportGenerator.inMultipleClasses(studentList);

        //assert students in multiple classes
        Assert.assertTrue(results.containsKey(2));
        Assert.assertTrue(results.get(2).contains(21));
        Assert.assertTrue(results.get(2).contains(22));

    }
}
