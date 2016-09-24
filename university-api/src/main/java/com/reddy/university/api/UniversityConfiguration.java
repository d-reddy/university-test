package com.reddy.university.api;

import io.dropwizard.Configuration;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityConfiguration extends Configuration{
    private String inputFile;


    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }
}
