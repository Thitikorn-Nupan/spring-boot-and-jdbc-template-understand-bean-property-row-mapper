package com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop;

import com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop.common.PrimaryKey;

import java.util.Date;
import java.util.UUID;

public class Employee extends PrimaryKey {
    private String firstname;
    private String lastname;
    private Date birthday;
    private String address;

    private static UUID getUuID() {
        return UUID.randomUUID();
    }

    public Employee(String firstname, String lastname, Date birthday, String address) {
        super(getUuID());
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.address = address;
    }


    public Employee() {
        super(null);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "uuid='" + getUuid() + '\'' +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                '}';
    }
}
