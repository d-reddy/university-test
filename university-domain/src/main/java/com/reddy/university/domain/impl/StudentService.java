package com.reddy.university.domain.impl;

import com.google.inject.Inject;
import com.reddy.university.domain.IStudentService;
import com.reddy.university.domain.IUniversityClassService;
import com.reddy.university.domain.mappers.StudentClassMapper;
import com.reddy.university.domain.mappers.UniversityClassMapper;
import com.reddy.university.domain.models.Student;
import com.reddy.university.domain.models.UniversityClass;
import com.reddy.university.repository.IStudentRepository;
import com.reddy.university.repository.IUniversityClassRepository;

import java.util.List;

/**
 * Created by deven on 9/24/2016.
 */
public class StudentService implements IStudentService {
    private IStudentRepository studentRepository;

    @Inject
    public StudentService(IStudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> get() throws Exception {
        return StudentClassMapper.toModels(studentRepository.get());
    }

}
