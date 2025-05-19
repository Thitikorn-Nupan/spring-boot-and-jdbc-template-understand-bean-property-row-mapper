package com.ttknp.understandbeanpropertyrowmapperjdbc;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Student;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.student.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UnderstandBeanPropertyRowMapperJdbcApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(UnderstandBeanPropertyRowMapperJdbcApplication.class);
    private final StudentService studentService;

    @Autowired
    public UnderstandBeanPropertyRowMapperJdbcApplication(StudentService studentService) {
        this.studentService = studentService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UnderstandBeanPropertyRowMapperJdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Student> students = studentService.findAll();
        for (var i = 0; i < students.size(); i++) {
            log.info("{}  " , students.get(i));
        }
    }
}
