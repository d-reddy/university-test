package com.reddy.university.domain;

import com.google.common.collect.Multimap;
import com.reddy.university.domain.models.UniversityClass;

import java.util.List;
import java.util.Map;

/**
 * Created by deven on 9/24/2016.
 */
public interface IUniversityClassService {
    List<UniversityClass> get() throws Exception;

//    Map<String,Integer> getStudentBreakdown() throws Exception;
//
//    Multimap<String,String> get() throws Exception;
}
