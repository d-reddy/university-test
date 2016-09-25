package com.reddy.university.api.reports;

import com.reddy.university.domain.models.Professor;
import com.reddy.university.domain.models.UniversityClass;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by deven on 9/25/2016.
 */
public class ProfessorReportGeneratorTest {

    @Test
    public void whenHasProfessorsTeachingMoreThan1Class_returnsOnlyProfessorsWhoTeachMoreThan1Class() throws Exception{

        List<Professor> professorList = new ArrayList<>();

        Professor jim = new Professor("Jim");
        jim.addClass("Mathematics");
        jim.addClass("History");
        jim.addClass("CS");
        professorList.add(jim);

        Professor max = new Professor("Max");
        max.addClass("CS");
        professorList.add(max);

        Professor x = new Professor("X");
        x.addClass("Psychology");
        x.addClass("Gymnastics");
        professorList.add(x);

        Map<Integer,List<String>> results = ProfessorReportGenerator.teachMultipleClasses(professorList);

        //assert professor results
        Assert.assertTrue(results.size() ==  1);
        Assert.assertTrue(results.get(2).size() == 2);
        Assert.assertTrue(results.get(2).contains("Jim"));
        Assert.assertTrue(results.get(2).contains("X"));
    }

    @Test
    public void whenProfessorHave2orMoreCommonStudentsInEachClass_returnsOnlyProfessorsWhoHave2orMoreCommonStudentsInEachClass() throws Exception {

        List<UniversityClass> universityClasses = new ArrayList<>();

        UniversityClass uClass1 = new UniversityClass("Mathematics", "Jim");
        uClass1.addStudent(21);
        uClass1.addStudent(22);
        uClass1.addStudent(23);
        universityClasses.add(uClass1);

        UniversityClass uClass2 = new UniversityClass("Psychology", "Jim");
        uClass2.addStudent(21);
        uClass2.addStudent(22);
        uClass2.addStudent(23);
        universityClasses.add(uClass2);

        UniversityClass uClass3 = new UniversityClass("Mathematics", "Max");
        uClass3.addStudent(21);
        uClass3.addStudent(22);
        universityClasses.add(uClass3);

        UniversityClass uClass4 = new UniversityClass("Psychology", "Max");
        uClass4.addStudent(21);
        uClass4.addStudent(22);
        uClass4.addStudent(23);
        universityClasses.add(uClass4);

        UniversityClass uClass5 = new UniversityClass("History", "Max");
        uClass5.addStudent(21);
        universityClasses.add(uClass5);

        Map<String, List<String>> results = ProfessorReportGenerator.withTwoOrMoreCommonStudentsInEachClass(universityClasses);

        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results.get("Jim").size(), 5);
        Assert.assertFalse(results.containsKey("Max"));
    }
}
