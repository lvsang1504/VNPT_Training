package com.devpro.train_19_07_2022;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Customer1 implements Parcelable {

     String firstName, lastName, Address;
    int Age;

    public Customer1(String fname, String lname, int age, String address) {

        firstName = fname;
        lastName = lname;
        Age = age;
        Address = address;
    }

    protected Customer1(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        Address = in.readString();
        Age = in.readInt();
    }

    public static final Creator<Customer1> CREATOR = new Creator<Customer1>() {
        @Override
        public Customer1 createFromParcel(Parcel in) {
            return new Customer1(in);
        }

        @Override
        public Customer1[] newArray(int size) {
            return new Customer1[size];
        }
    };

    public String printValues() {

        String data = null;

        data = "First Name :" + firstName + " Last Name :" + lastName
                + " Age : " + Age + " Address : " + Address;

        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(Address);
        parcel.writeInt(Age);
    }
}
