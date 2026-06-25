package com.designpatterns.factory;

public abstract class DocumentFactory {

    // Factory method - subclasses decide which Document to create
    public abstract Document createDocument();

    // Common workflow using factory method
    public void openDocument() {
        Document doc = createDocument();
        doc.open();
        doc.save();
        doc.close();
    }
}
