package com.reddy.university.domain.impl;

import com.google.inject.Inject;
import com.reddy.university.domain.IUniversityClassService;
import com.reddy.university.domain.mappers.UniversityClassMapper;
import com.reddy.university.domain.models.UniversityClass;
import com.reddy.university.repository.IUniversityClassRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by deven on 9/24/2016.
 */
public class UniversityClassService implements IUniversityClassService {
    private IUniversityClassRepository universityClassRepository;

    @Inject
    public UniversityClassService(IUniversityClassRepository universityClassRepository){
        this.universityClassRepository = universityClassRepository;
    }

    @Override
    public List<UniversityClass> get() throws Exception {
        UniversityClassMapper mapper = new UniversityClassMapper();
        return mapper.toModels(universityClassRepository.get());
    }

    @Override
    public Map<String,Integer> getStudentBreakdown() throws Exception {
        UniversityClassMapper mapper = new UniversityClassMapper();
        List<UniversityClass> classes = mapper.toModels(universityClassRepository.get());

        Map<String, Integer> classGroup =
                classes.stream().collect(Collectors.groupingBy(UniversityClass::getName, Collectors.summingInt(UniversityClass::getRegisteredStudents)));

        return classGroup;
    }
}
