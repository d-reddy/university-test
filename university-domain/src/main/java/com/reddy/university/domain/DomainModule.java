package com.reddy.university.domain;

import com.google.inject.AbstractModule;
import com.reddy.university.domain.impl.ReportService;
import com.reddy.university.domain.impl.UniversityClassService;
import com.reddy.university.repository.IDatabase;
import com.reddy.university.repository.IProfessorRepository;
import com.reddy.university.repository.IStudentRepository;
import com.reddy.university.repository.IUniversityClassRepository;
import com.reddy.university.repository.impl.Database;
import com.reddy.university.repository.impl.ProfessorRepository;
import com.reddy.university.repository.impl.StudentRepository;
import com.reddy.university.repository.impl.UniversityClassRepository;

/**
 * Created by deven on 9/24/2016.
 */
public class DomainModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IDatabase.class).to(Database.class);
        //bind(IProfessorRepository.class).to(ProfessorRepository.class);
        //bind(IStudentRepository.class).to(StudentRepository.class);
        bind(IUniversityClassService.class).to(UniversityClassService.class);
        bind(IUniversityClassRepository.class).to(UniversityClassRepository.class);
        bind(IReportService.class).to(ReportService.class);
    }
}
