package com.reddy.university.api.models.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClassStudentSummary extends UniversityClassSummary {

    private Integer registeredStudents;
    public List<Integer> students = new ArrayList<>();

    public UniversityClassStudentSummary(String name, String professor) {
        super(name,professor);
    }

    public Integer getRegisteredStudents() {
        return students.size();
    }

}
