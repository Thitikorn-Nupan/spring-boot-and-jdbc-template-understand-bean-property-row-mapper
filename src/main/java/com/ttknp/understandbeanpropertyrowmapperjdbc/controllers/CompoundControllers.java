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
import org.springframework.web.bind.annotation.ResponseBody;

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
    public static class StudentController {
        @GetMapping
        public List<Student> getStudents() {
            return CompoundControllers.studentService.findAll();
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
