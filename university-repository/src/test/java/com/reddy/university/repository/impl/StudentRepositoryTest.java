package com.reddy.university.repository.impl;

import com.reddy.university.repository.IDatabase;
import com.reddy.university.repository.IStudentRepository;
import com.reddy.university.repository.entities.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by deven on 9/25/2016.
 */
public class StudentRepositoryTest {
    private IDatabase databaseMock;
    private IStudentRepository studentRepository;

    @Before
    public void setup() throws Exception {
        List<Student> students = new ArrayList<>();

        Student student = new Student(23);
        students.add(student);

        databaseMock = mock(Database.class);
        studentRepository = new StudentRepository(databaseMock);

        doReturn(students).when(databaseMock).getStudents();
    }

    @Test
    public void whenGetStudentsCalled_hitsDatabaseMock1Time() throws Exception {

        studentRepository.get();
        verify(databaseMock,times(1)).getStudents();
    }
}
