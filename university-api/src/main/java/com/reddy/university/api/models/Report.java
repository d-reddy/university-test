package com.reddy.university.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.collections.map.MultiValueMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deven on 9/24/2016.
 */
public class Report {
    @JsonProperty(value = "Class Sections Taught")
    public MultiValueMap universityClassReport;

    @JsonProperty(value = "How many students are registered for each class")
    public Map<String, Integer>  universityClassBreakdown;

    @JsonProperty(value = "Classes Taken by Each Student")
    public Map<Integer,List<String>> studentClassReport;

    @JsonProperty(value = "How many students take more than one class")
    public Map<Integer,List<Integer>> studentClassCountReport;

    @JsonProperty(value = "How many professors teach more than one class")
    public Map<Integer,List<String>> professorClassCountReport;

    @JsonProperty(value = "Professors with at least 2 classes with 2 or more of the same students")
    public Map<String, List<String>> professorWith2OrMoreOfSameStudent;
}
