package com.devpro.train_19_07_2022;

import java.io.Serializable;

public class Customer implements Serializable {

     String firstName, lastName, Address;
    int Age;

    public Customer(String fname, String lname, int age, String address) {

        firstName = fname;
        lastName = lname;
        Age = age;
        Address = address;
    }

    public String printValues() {

        String data = null;

        data = "First Name :" + firstName + " Last Name :" + lastName
                + " Age : " + Age + " Address : " + Address;

        return data;
    }
}
