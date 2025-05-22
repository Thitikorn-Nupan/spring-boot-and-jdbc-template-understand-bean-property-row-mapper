package com.ttknp.understandbeanpropertyrowmapperjdbc.helpers.sql_commands;

// Note H2 version 2.3.2
public class Commands {
    // NamedParam Jdbc
    public static final String STUDENT_INSERT = "INSERT INTO TTKNP_SCHOOL.STUDENTS( FULL_NAME, BIRTHDAY, LEVEL) VALUES(:fullName, :birthday, :level)"; // map property of object/pojo
    public static final String STUDENT_SELECT_COUNT_LEVEL = "SELECT COUNT(*) FROM TTKNP_SCHOOL.STUDENTS WHERE level = :level"; // map property of object/pojo
    // Jdbc
    public static final String STUDENT_DELETE_ONE_BY_PK = "DELETE FROM TTKNP_SCHOOL.STUDENTS WHERE SID = ?;";
    public static final String STUDENT_DELETE_ONE_BY_FULL_NAME = "DELETE FROM TTKNP_SCHOOL.STUDENTS WHERE FULL_NAME = ?;";
    public static final String STUDENT_SELECT_ALL = "SELECT * FROM TTKNP_SCHOOL.STUDENTS";
    public static final String STUDENT_SELECT_ALL_BY_LEVEL = "SELECT * FROM TTKNP_SCHOOL.STUDENTS WHERE LEVEL = ?";
    public static final String STUDENT_SELECT_ALL_ONLY_FULL_NAME = "SELECT FULL_NAME FROM TTKNP_SCHOOL.STUDENTS";
    public static final String STUDENT_SELECT_ALL_ONLY_SID = "SELECT SID FROM TTKNP_SCHOOL.STUDENTS";
    public static final String STUDENT_SELECT_ONE_BY_PK = "SELECT * FROM TTKNP_SCHOOL.STUDENTS WHERE SID = ?";
    public static final String STUDENT_SELECT_ONE_BY_FULL_NAME_AND_PK = "SELECT * FROM TTKNP_SCHOOL.STUDENTS WHERE FULL_NAME = ? AND SID = ?";
    public static final String STUDENT_SELECT_ONE_BY_FULL_NAME_AND_LEVEL = "SELECT * FROM TTKNP_SCHOOL.STUDENTS WHERE FULL_NAME = ? AND LEVEL = ?";

    // Jdbc
    public static final String TEACHER_SELECT_ALL = "SELECT * FROM TTKNP_SCHOOL.TEACHERS";
    public static final String TEACHER_SELECT_ALL_ONLY_FULL_NAME = "SELECT FULL_NAME FROM TTKNP_SCHOOL.TEACHERS";
    public static final String TEACHER_SELECT_ALL_ONLY_TID = "SELECT TID FROM TTKNP_SCHOOL.TEACHERS";
    public static final String TEACHER_SELECT_ONE_BY_PK = "SELECT * FROM TTKNP_SCHOOL.TEACHERS WHERE TID = ?";
    public static final String TEACHER_SELECT_ONE_BY_FULL_NAME_AND_PK = "SELECT * FROM TTKNP_SCHOOL.TEACHERS WHERE FULL_NAME = ? AND TID = ?";
    public static final String TEACHER_SELECT_ONE_BY_FULL_NAME_AND_CLASS_ID = "SELECT * FROM TTKNP_SCHOOL.TEACHERS WHERE FULL_NAME = ? AND CLASS_ID = ?";


    // NamedParam Jdbc
    public static final String CUSTOMER_INSERT = "INSERT INTO TTKNP_SHOP.CUSTOMERS (UUID, FIRSTNAME, LASTNAME, BIRTHDAY, ADDRESS) VALUES (:uuid, :firstname, :lastname, :birthday, :address)"; // map property of object/pojo
    // Jdbc
    public static final String CUSTOMER_SELECT_COUNT = "SELECT COUNT(*) FROM TTKNP_SHOP.CUSTOMERS"; // map property of object/pojo
    public static final String CUSTOMER_SELECT_ALL = "SELECT * FROM TTKNP_SHOP.CUSTOMERS";
    public static final String CUSTOMER_SELECT_ALL_BY_LASTNAME = "SELECT * FROM TTKNP_SHOP.CUSTOMERS WHERE LASTNAME = ?";
    public static final String  CUSTOMER_SELECT_ALL_FIRSTNAME_START_WITH = "SELECT * FROM TTKNP_SHOP.CUSTOMERS WHERE FIRSTNAME LIKE";


    // NamedParam Jdbc
    public static final String EMPLOYEE_INSERT = "INSERT INTO TTKNP_SHOP.EMPLOYEES (UUID, FIRSTNAME, LASTNAME, BIRTHDAY, ADDRESS) VALUES (:uuid, :firstname, :lastname, :birthday, :address)"; // map property of object/pojo
    // Jdbc
    public static final String EMPLOYEE_SELECT_COUNT = "SELECT COUNT(*) FROM TTKNP_SHOP.EMPLOYEES";
    public static final String EMPLOYEE_SELECT_ALL = "SELECT * FROM TTKNP_SHOP.EMPLOYEES";
    public static final String EMPLOYEE_SELECT_ALL_BY_BIRTHDAY = "SELECT * FROM TTKNP_SHOP.EMPLOYEES WHERE BIRTHDAY = ?";
    public static final String EMPLOYEE_SELECT_ALL_FIRSTNAME_START_WITH = "SELECT * FROM TTKNP_SHOP.EMPLOYEES WHERE FIRSTNAME LIKE";


}
