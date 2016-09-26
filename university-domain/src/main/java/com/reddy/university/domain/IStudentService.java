package com.reddy.university.domain;

import com.reddy.university.domain.models.Student;
import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public interface IStudentService {
    List<Student> get() throws Exception;
}
