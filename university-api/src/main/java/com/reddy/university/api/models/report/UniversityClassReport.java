package com.reddy.university.api.models.report;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClassReport {

    @JsonProperty("classes")
    public List<UniversityClassSummary> classSummaries;
}
