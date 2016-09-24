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

/**
 * Created by deven on 9/23/2016.
 */
public class Database {
    private List<Student> students = new ArrayList<>();
    private List<UniversityClass> classes = new ArrayList<>();
    private List<Professor> professors = new ArrayList<>();

    private String currentMd5 = "";
    private String file;

    public Database(String file){
        this.file = file;
    }

    private void build() /*throws Exception*/{
        try {
            FileInputStream fis = new FileInputStream(new File(file));
            String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);

            if(!currentMd5.equals(md5)){
                Reader in = new FileReader(file);
                Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
                for (CSVRecord record : records) {
                    String uClass = record.get(0);
                    String professor = record.get(1);
                    String student = record.get(2);
                    classes.add(new UniversityClass(uClass));
                }
                in.close();
            }

            fis.close();
        }catch(Exception e){
            //throw e;
        }
    }

    public List<Student> getStudents() {
        build();
        return students;
    }

    public List<UniversityClass> getClasses() {
        build();
        return classes;
    }

    public List<Professor> getProfessors() {
        build();
        return professors;
    }
}
