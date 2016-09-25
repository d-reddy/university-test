package com.reddy.university.domain;

import com.reddy.university.domain.models.UniversityClass;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public interface IUniversityClassService {
    List<UniversityClass> get() throws Exception;
}
