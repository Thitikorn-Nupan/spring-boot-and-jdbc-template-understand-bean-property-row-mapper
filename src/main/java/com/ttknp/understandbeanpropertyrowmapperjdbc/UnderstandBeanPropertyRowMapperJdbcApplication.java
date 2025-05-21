package com.ttknp.understandbeanpropertyrowmapperjdbc;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Student;
import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.school.Teacher;
import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop.Customer;
import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop.Employee;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.school.StudentService;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.school.TeacherService;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop.CustomerService;
import com.ttknp.understandbeanpropertyrowmapperjdbc.services.shop.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class UnderstandBeanPropertyRowMapperJdbcApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(UnderstandBeanPropertyRowMapperJdbcApplication.class);
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    @Autowired
    public UnderstandBeanPropertyRowMapperJdbcApplication(StudentService studentService,
                                                          TeacherService teacherService,
                                                          CustomerService customerService,
                                                          EmployeeService employeeService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.customerService = customerService;
        this.employeeService = employeeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(UnderstandBeanPropertyRowMapperJdbcApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        // testStudentService();
        // testTeacherService();
        // testCustomerService();
        testEmployeeService();
    }

    private void testEmployeeService() {
        /*
        List<Employee> employees = employeeService.readsAsListFilterBy("1999-09-19");
        for (Employee employee : employees) {
            log.info("{}  ", employee);
        }
        */
        log.info("multiple queries {}",employeeService.deleteAndBackup("Chan"));
    }

    private void testCustomerService() throws ParseException {
        // Customer customer = new Customer("Alun","Jonson",getBirthday("19-09-1999"),"21/96-98 Moo 6 Ekachai Road Bangbon");
        // log.info("row affected {}", customerService.save(customer));
        // log.info("total rows {}", customerService.countRows());

        // List<Customer> customers = customerService.readsAsListFilterBy("Jonson");
        // for (Customer customer : customers) {
        //     log.info("{}  ", customer);
        // }

        log.info("multiple queries {}",customerService.deleteAndBackup("59aef92e-3d50-45d9-bdfb-39051c6df16f"));
    }

    private void testTeacherService() {

        /*List<Teacher> teachers = teacherService.findAll();
        for (var i = 0; i < teachers.size(); i++) {
            log.info("{}  " , teachers.get(i));
        }*/


        // log.info("{}",teacherService.findByPrimaryKey(1L));

        // log.info("{}",teacherService.findByFullNameAndLevel("Adam Slider","AS990891ST"));

        // log.info("{}",studentService.findByFullNameAndPrimaryKey("Adam Slider",1L));

        /*List<Map<String,Object>> students = teacherService.findAll("");
        for (var i = 0; i < students.size(); i++) {
            log.info("{}  " , students.get(i));
        }

        List<String> teachersFullName = teacherService.findAllFullName();
        for (var i = 0; i < teachersFullName.size(); i++) {
            log.info("{}  " , teachersFullName.get(i));
        }


        List<Long> teachersTid = teacherService.findAllTid();
        for (var i = 0; i < teachersTid.size(); i++) {
            log.info("{}  " , teachersTid.get(i));
        }*/
    }

    private void testStudentService() {
        /*
        List<Student> students = studentService.findAll();
        for (var i = 0; i < students.size(); i++) {
            log.info("{}  " , students.get(i));
        }
        */

        // log.info("{}",studentService.findByPrimaryKey(1L));

        // log.info("{}",studentService.findByFullNameAndLevel("Adam Slider","A+"));

        // log.info("{}",studentService.findByFullNameAndPrimaryKey("Adam Slider",1L));

        /*
        List<Map<String,Object>> students = studentService.findAll("");
        for (var i = 0; i < students.size(); i++) {
            log.info("{}  " , students.get(i));
        }
        */

        /*
        List<String> studentsFullName = studentService.findAllFullName();
        for (var i = 0; i < studentsFullName.size(); i++) {
            log.info("{}  " , studentsFullName.get(i));
        }


        List<Long> studentsSid = studentService.findAllSid();
        for (var i = 0; i < studentsSid.size(); i++) {
            log.info("{}  " , studentsSid.get(i));
        }
        */

        // log.info("row affected {}",studentService.save(new Student(0L,"Stone Austin",new Date(2000,12,1),"C+")));

        // log.info("row affected {}",studentService.countLevel(new Student(0L,"",new Date(),"A")));

        // log.info("row affected {}",studentService.deleteByPkOrFullName(new Student(4L,null,null,null)));
        // log.info("row affected {}",studentService.deleteByPkOrFullName(new Student(null,"Austin Ryder",null,null)));


        List<Student> students = studentService.findAllFilterLevel("A");
        for (Student student : students) {
            log.info("{}  ", student);
        }
    }

    // format "19-09-1999"
    private Date getBirthday(String birthdayAsDayMonthYear) throws ParseException {
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.parse(birthdayAsDayMonthYear);
    }
}

