package com.designpatterns.singleton;

public class Logger {

    // Private static instance
    private static Logger instance;

    // Private constructor
    private Logger() {
        System.out.println("Logger instance created!");
    }

    // Public static method - thread safe
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }

    public void warn(String message) {
        System.out.println("[WARN] " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
