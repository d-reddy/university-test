package com.reddy.university.api.models.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by deven on 9/24/2016.
 */
public class Report {
    @JsonProperty(value = "List of Class Sections Being Taught")
    public UniversityClassReport universityClassReport;

    @JsonProperty(value = "List of Classes Being Taken by Each Student")
    public StudentClassReport studentClassReport;

}
