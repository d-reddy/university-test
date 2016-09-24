package com.reddy.university.repository;

import com.reddy.university.repository.entities.Professor;
import com.reddy.university.repository.entities.Student;
import com.reddy.university.repository.entities.UniversityClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by deven on 9/23/2016.
 */
public class Database {
    private List<Student> students = new ArrayList<>();
    private List<UniversityClass> classes = new ArrayList<>();
    private List<Professor> professors = new ArrayList<>();

    private String currentMd5 = "";
    private File file;

    public Database(File file){
        this.file = file;
    }

    public List<Student> getStudents() throws Exception {
        build();
        return students;
    }

    public List<UniversityClass> getClasses() throws Exception{
        build();
        return classes;
    }

    public List<Professor> getProfessors() throws Exception {
        build();
        return professors;
    }

    private void build() throws Exception{
        try (FileInputStream fis = new FileInputStream(file)) {

            String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);

            if(!currentMd5.equals(md5)){

                students.clear();
                classes.clear();
                professors.clear();

                try (Reader in = new FileReader(file)){
                    Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
                    for (CSVRecord record : records) {
                        buildEntityRelationships(record);
                    }
                }
            }

        } catch(Exception e){
            Exception f = new Exception("error reading db", e);
            throw f;
        }
    }

    private void buildEntityRelationships(CSVRecord record) throws Exception {
        String universityClass = record.get(0).toLowerCase().replaceAll("\\s+","");
        String professor = record.get(1).toLowerCase().replaceAll("\\s+","");
        Integer studentId = Integer.parseInt(record.get(2).replaceAll("\\s+",""));

        List<Student> foundStudents = students.stream().filter(s -> s.getId().equals(studentId)).collect(Collectors.toList());
        List<Professor> foundProfessors = professors.stream().filter(s -> s.getName().equals(professor)).collect(Collectors.toList());
        List<UniversityClass> foundClasses = classes.stream().filter(s -> s.getName().equals(universityClass) && s.getProfessor().getName().equals(professor)).collect(Collectors.toList());

        Student theStudent;
        UniversityClass theUniversityClass;
        Professor theProfessor;

        if (foundProfessors.size() > 1){
            throw new Exception("data integrity violation");
        } else if (foundProfessors.size() == 1) {
            theProfessor = foundProfessors.get(0);
        } else {
            theProfessor = new Professor(professor);
            professors.add(theProfessor);
        }

        if (foundClasses.size() > 1){
            throw new Exception("data integrity violation");
        } else if (foundClasses.size() == 1) {
            theUniversityClass = foundClasses.get(0);
        } else {
            theUniversityClass = new UniversityClass(universityClass, theProfessor);
            theProfessor.addUniversityClass(theUniversityClass);
            classes.add(theUniversityClass);
        }

        if (foundStudents.size() > 1){
            throw new Exception("data integrity violation");
        } else if (foundStudents.size() == 1){
            theStudent = foundStudents.get(0);
            if(!theStudent.getUniversityClasses().contains(universityClass)){
                theUniversityClass.addStudent(theStudent);
                theStudent.addUniversityClass(theUniversityClass);
            }
        } else {
            theStudent = new Student(studentId);
            theUniversityClass.addStudent(theStudent);
            theStudent.addUniversityClass(theUniversityClass);
            students.add(theStudent);
        }
    }
}
