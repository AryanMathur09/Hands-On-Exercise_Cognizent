package com.designpatterns.singleton;

public class LoggerTest {

    public static void main(String[] args) {

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        Logger logger3 = Logger.getInstance();

        logger1.log("Application started");
        logger2.warn("Low memory warning");
        logger3.error("Null pointer encountered");

        System.out.println("\n--- Singleton Verification ---");
        System.out.println("logger1 == logger2 : " + (logger1 == logger2));
        System.out.println("logger2 == logger3 : " + (logger2 == logger3));
        System.out.println("logger1 hashcode: " + logger1.hashCode());
        System.out.println("logger2 hashcode: " + logger2.hashCode());
        System.out.println("logger3 hashcode: " + logger3.hashCode());
    }
}