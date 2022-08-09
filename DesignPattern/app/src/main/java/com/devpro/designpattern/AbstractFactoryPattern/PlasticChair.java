package com.devpro.designpattern.AbstractFactoryPattern;

public class PlasticChair implements Chair{
    @Override
    public void create() {
        System.out.println("Create plastic chair");
    }
}
