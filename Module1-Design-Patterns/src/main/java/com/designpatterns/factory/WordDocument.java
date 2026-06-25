package com.designpatterns.factory;

public class WordDocument implements Document {

    @Override
    public void open() {
        System.out.println("Opening Word Document (.docx)");
    }

    @Override
    public void save(){
        System.out.println("Saving Word Document (.docx)");
    }
    @Override
    public void close(){
        System.out.println("Closing Word Document (.docx)");
    }
}
