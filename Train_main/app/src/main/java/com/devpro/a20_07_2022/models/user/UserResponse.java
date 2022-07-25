package com.devpro.a20_07_2022.models.user;

import java.util.ArrayList;



 class Datum{
    public int id;
    public String name;
    public int year;
    public String color;
    public String pantone_value;
}

public class UserResponse{
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<Datum> data;
    public Support support;
}

 class Support{
    public String url;
    public String text;
}
