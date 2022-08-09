package com.devpro.designpattern.AbstractFactoryPattern;

public class PlasticTable implements Table {
    @Override
    public void create() {
        System.out.println("Create plastic table");
    }
}
