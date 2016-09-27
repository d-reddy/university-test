package com.reddy.university.repository.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.reddy.university.repository.IDatabase;
import com.reddy.university.repository.entities.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by deven on 9/23/2016.
 *
 * This class is playing the role of the database.  It is responsible for holding onto the data and
 * maintaining the entity relationships between university class, professor, and students.
 *
 */
public class Database implements IDatabase {
    private List<Student> students = Collections.synchronizedList(new ArrayList<>());
    private List<UniversityClass> classes = Collections.synchronizedList(new ArrayList<>());
    private List<Professor> professors = Collections.synchronizedList(new ArrayList<>());

    private String currentMd5 = "";
    private File file;

    public Database(File file){
        this.file = file;
    }

    @Inject
    public Database(@Named("inputFile") String inputFile){
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceLocation = classLoader.getResource(inputFile);

        //use class resource if defined, otherwise use absolute path
        if(resourceLocation != null){
            this.file = new File(resourceLocation.getFile());
        }else{
            this.file = new File(FilenameUtils.separatorsToSystem(inputFile));
        }
    }

    /**
     * return built students
     *
     * @return list of student entities
     * @throws Exception
     */
    @Override
    public List<Student> getStudents() throws Exception {
        build();
        return students;
    }

    /**
     * return built university classes
     *
     * @return list of university class entities
     * @throws Exception
     */
    @Override
    public List<UniversityClass> getUniversityClasses() throws Exception{
        build();
        return classes;
    }

    /**
     * return built professors
     *
     * @return list of professors
     * @throws Exception
     */
    @Override
    public List<Professor> getProfessors() throws Exception {
        build();
        return professors;
    }

    /**
     * using the csv file, determine if the relationship tree needs to be rebuilt, if so rebuild it.
     * @throws Exception
     */
    private void build() throws Exception{
        try (FileInputStream fis = new FileInputStream(file)) {

            String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);

            //has the file been updated, if not, there's nothing to rebuild, use existing repo entities
            if(!currentMd5.equals(md5)){
                buildEntityRelationships();
            }

        }
        catch(FileNotFoundException e){
            throw e;
        }
        catch(Exception e){
            throw e;
        }
    }

    /**
     * this function will build out the entity relationship tree for the university class, professor, and students
     * its a synchronized method as we don't want multiple threads building the db at the same time
     * @param
     * @throws Exception
     */
    private synchronized void buildEntityRelationships(/*CSVRecord record*/) throws Exception {

        //if so, then we need to clear out our content, and rebuild
        students.clear();
        classes.clear();
        professors.clear();

        try (Reader in = new FileReader(file)){
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

            //process each csv record
            for (CSVRecord record : records) {
                //build relationship tree with each record.
                //buildEntityRelationships(record);
                try {
                    //remove spaces from the record fields
                    String universityClass = record.get(0).replaceAll("\\s+","");
                    String professor = record.get(1).replaceAll("\\s+","");
                    Integer studentId = Integer.parseInt(record.get(2).replaceAll("\\s+",""));

                    //pull existing information for each entity if it already exists to build on it if needed
                    List<Student> foundStudents = students.stream()
                            .filter(s -> s.getId().equals(studentId))
                            .collect(Collectors.toList());

                    List<Professor> foundProfessors = professors.stream()
                            .filter(s -> s.getName().equals(professor))
                            .collect(Collectors.toList());

                    List<UniversityClass> foundClasses = classes.stream()
                            .filter(s -> s.getName().equals(universityClass) && s.getProfessor().getName().equals(professor))
                            .collect(Collectors.toList());

                    //track current student, class, and professor the record references
                    Student theStudent;
                    UniversityClass theUniversityClass;
                    Professor theProfessor;

                    //if there exists more than 1 professor with the same name (which should be unique), then throw an error
                    if (foundProfessors.size() > 1){
                        throw new Exception("data integrity violation");
                    } else if (foundProfessors.size() == 1) {
                        //professor exists, set active professor
                        theProfessor = foundProfessors.get(0);
                    } else {
                        //if professor does not exist, create a new one and add to the list
                        theProfessor = new Professor(professor);
                        professors.add(theProfessor);
                    }

                    //if there exists more than 1 class with the same name/prof pair (which should be unique), then throw an error
                    if (foundClasses.size() > 1){
                        throw new Exception("data integrity violation");
                    } else if (foundClasses.size() == 1) {
                        //class exists, set active class
                        theUniversityClass = foundClasses.get(0);
                    } else {
                        //if class does not exist, create a new one and add to the list
                        theUniversityClass = new UniversityClass(universityClass, theProfessor);
                        //a class must have both a name and prof associated with it.
                        theProfessor.addUniversityClass(theUniversityClass);
                        classes.add(theUniversityClass);
                    }

                    //if there exists more than 1 student with the same id (which should be unique), then throw an error
                    if (foundStudents.size() > 1){
                        throw new Exception("data integrity violation");
                    } else if (foundStudents.size() == 1){
                        //student exists, set active
                        theStudent = foundStudents.get(0);
                        //add class to student relationship tree, if needed, and update class student list as well
                        if(!theStudent.getUniversityClasses().contains(universityClass)){
                            theUniversityClass.addStudent(theStudent);
                            theStudent.addUniversityClass(theUniversityClass);
                        }
                    } else {
                        //create a new student and add it to list
                        theStudent = new Student(studentId);
                        //add student to class
                        theUniversityClass.addStudent(theStudent);
                        //add class to student
                        theStudent.addUniversityClass(theUniversityClass);
                        students.add(theStudent);
                    }

                }catch(Exception ex){
                    throw new Exception("data integrity violation, bad csv or invalid record set", ex);
                }
            }
        }
    }
}
