package com.ttknp.understandbeanpropertyrowmapperjdbc.controllers;

import com.ttknp.understandbeanpropertyrowmapperjdbc.annotations.CommonRestAPI;
import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Student;
import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Teacher;
import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop.Customer;
import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop.Employee;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.school.StudentService;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.school.TeacherService;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop.CustomerService;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@CommonRestAPI
public class CompoundControllers {

    protected static StudentService studentService;
    protected static TeacherService teacherService;
    protected static CustomerService customerService;
    protected static EmployeeService employeeService;

    @Autowired
    public CompoundControllers(StudentService studentService,
                               TeacherService teacherService,
                               CustomerService customerService,
                               EmployeeService employeeService
                               ) {
        CompoundControllers.studentService = studentService;
        CompoundControllers.teacherService = teacherService;
        CompoundControllers.customerService = customerService;
        CompoundControllers.employeeService = employeeService;
    }

    @GetMapping("/server")
    @ResponseBody
    protected String testServer() {
        return "running on port 8080";
    }

    @CommonRestAPI(path = "/student")
    public static class StudentController { // this for new concept

        @GetMapping
        public List<Student> getStudents() {
            return CompoundControllers.studentService.findAll();
        }

        @GetMapping("/reads/column")
        public List<Object> getStudentsOnlyColumn(@RequestParam("name") String name) {
            return CompoundControllers.studentService.findAllOnlyColumnName(name);
        }

        @GetMapping("/read/0.1") // student/read/0.1?uniqKey=SID&value=3
        public Student getStudentByUniqKeyByPK(@RequestParam("uniqKey") String uniqKey,@RequestParam("value") Long value) {
            return CompoundControllers.studentService.findByUniqKey(uniqKey, value);
        }

        @GetMapping("/read/0.2") // student/read/0.2?uniqKey=FULL_NAME&value=Jason%20Owner
        public Student getStudentByUniqKeyByFullName(@RequestParam("uniqKey") String uniqKey,@RequestParam("value") String value) {
            return CompoundControllers.studentService.findByUniqKey(uniqKey, value);
        }

        @GetMapping("/read/fullNameBy")
        public String getStudentOnlyFullName(@RequestParam("pk") Long pk) {
            return CompoundControllers.studentService.findFullNameBySID(pk);
        }

        @GetMapping("/read/birthdayBy")
        public String getStudentOnlyBirthday(@RequestParam("pk") Long pk) {
            Date birthday = CompoundControllers.studentService.findBirthdayBySID(pk);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(birthday);
        }



    }

    @CommonRestAPI(path =  "/teacher")
    static class TeacherController {
        @GetMapping
        public List<Teacher> getTeachers() {
            return CompoundControllers.teacherService.findAll();
        }
    }

    @CommonRestAPI(path = "/customer")
    static class CustomerController {
        @GetMapping
        public List<Customer> getCustomers() {
            return CompoundControllers.customerService.readsAsList();
        }
    }

    @CommonRestAPI(path = "/employee")
    static class EmployeeController {
        @GetMapping
        public List<Employee> getEmployees() {
            return CompoundControllers.employeeService.readsAsList();
        }
    }

}
