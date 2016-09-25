package com.reddy.university.api.models.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.map.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deven on 9/24/2016.
 */
public class Report {
    @JsonProperty(value = "Class Sections Taught")
    //public UniversityClassReport universityClassReport;
    public MultiValueMap universityClassReport;

    @JsonProperty(value = "Classes Taken by Each Student")
    public HashMap<Integer,List<String>> studentClassReport;
//    @JsonProperty(value = "Classes Taken By Each Student")
//    public StudentClassReport studentClassReport;
//
//    @JsonProperty(value = "How many students are registered for each class")
//    public Multimap<String,String> universityClassBreakdown;

}
