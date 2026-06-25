package com.designpatterns.factory;

public class DocumentFactoryTest {

    public static void main(String[] args) {

        System.out.println("=== Document Management System ===\n");

        // Word Document
        System.out.println("--- Word Document ---");
        DocumentFactory wordFactory = new WordDocumentFactory();
        wordFactory.openDocument();

        System.out.println();

        // PDF Document
        System.out.println("--- PDF Document ---");
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        pdfFactory.openDocument();

        System.out.println();

        // Excel Document
        System.out.println("--- Excel Document ---");
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        excelFactory.openDocument();

        System.out.println();

        // Direct document creation
        System.out.println("--- Direct Document Creation ---");
        Document doc = wordFactory.createDocument();
        doc.open();
        doc.save();
        doc.close();
    }
}