package com.devpro.designpattern.AbstractFactoryPattern;

public class WoodChair implements Chair{
    @Override
    public void create() {
        System.out.println("Create wood chair");
    }
}
