package com.reddy.university.api.reports;

import com.reddy.university.domain.models.UniversityClass;
import org.apache.commons.collections.map.MultiValueMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deven on 9/25/2016.
 */
public class UniversityClassGeneratorTest {

    @Test
    public void whenHas3StudentInMathematics4StudentsInHistory3StudentsInPhysics_returnsCorrectMap() throws Exception{

        List<UniversityClass> universityClassList = new ArrayList<>();

        UniversityClass universityClass1 = new UniversityClass("Mathematics","Jim");
        universityClass1.addStudent(21);
        universityClass1.addStudent(22);
        universityClassList.add(universityClass1);

        UniversityClass universityClass2 = new UniversityClass("Mathematics","John");
        universityClass1.addStudent(23);
        universityClassList.add(universityClass2);

        UniversityClass universityClass3 = new UniversityClass("History","Matt");
        universityClass3.addStudent(21);
        universityClass3.addStudent(22);
        universityClassList.add(universityClass3);

        UniversityClass universityClass4 = new UniversityClass("History","Scott");
        universityClass4.addStudent(23);
        universityClassList.add(universityClass4);

        UniversityClass universityClass5 = new UniversityClass("History","Henry");
        universityClass5.addStudent(24);
        universityClassList.add(universityClass5);

        UniversityClass universityClass6 = new UniversityClass("Physics","Matt");
        universityClass6.addStudent(21);
        universityClass6.addStudent(22);
        universityClass6.addStudent(23);
        universityClassList.add(universityClass6);

        Map<String, Integer> results = UniversityClassReportGenerator.universityClassBreakdown(universityClassList);

        //assert class student breakdown
        Assert.assertTrue(results.size() ==  3);
        Assert.assertTrue(results.get("Mathematics") == 3);
        Assert.assertTrue(results.get("History") == 4);
        Assert.assertTrue(results.get("Physics") == 3);
    }


    @Test
    public void whenHasUniqueClassProfessorPairs_returnsCorrectMap() throws Exception{

        List<UniversityClass> universityClassList = new ArrayList<>();

        UniversityClass universityClass1 = new UniversityClass("Mathematics","Jim");
        universityClassList.add(universityClass1);

        UniversityClass universityClass2 = new UniversityClass("Mathematics","John");
        universityClassList.add(universityClass2);

        UniversityClass universityClass3 = new UniversityClass("History","Matt");
        universityClassList.add(universityClass3);

        UniversityClass universityClass4 = new UniversityClass("History","Scott");
        universityClassList.add(universityClass4);

        UniversityClass universityClass5 = new UniversityClass("History","Henry");
        universityClassList.add(universityClass5);

        UniversityClass universityClass6 = new UniversityClass("Physics","Matt");
        universityClassList.add(universityClass6);

        UniversityClass universityClass7 = new UniversityClass("Physics","Ben");
        universityClassList.add(universityClass7);

        MultiValueMap results = UniversityClassReportGenerator.universityClasses(universityClassList);

        //assert classes returned
        Assert.assertTrue(results.size() ==  3);
        Assert.assertTrue(((List)results.get("Mathematics")).size() == 2);
        Assert.assertTrue(((List)results.get("History")).size() == 3);
        Assert.assertTrue(((List)results.get("Physics")).size() == 2);

    }
}
