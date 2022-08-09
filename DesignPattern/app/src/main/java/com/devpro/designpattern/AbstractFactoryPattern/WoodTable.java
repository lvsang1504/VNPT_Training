package com.devpro.designpattern.AbstractFactoryPattern;

public class WoodTable implements Table{
    @Override
    public void create() {
        System.out.println("Create wood table");
    }
}
