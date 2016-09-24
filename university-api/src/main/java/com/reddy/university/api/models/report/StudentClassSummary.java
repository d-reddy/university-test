package com.reddy.university.api.models.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class StudentClassSummary {
    public Integer id;
    public List<String> classes = new ArrayList<>();

    public StudentClassSummary(Integer id){
        this.id = id;
    }
}
