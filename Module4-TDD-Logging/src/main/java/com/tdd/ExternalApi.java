package com.tdd;

// Simulates an external API (like a REST API or Database)
public class ExternalApi {

    public String getData() {
        // In real world this would call an actual API
        return "Real API Data";
    }

    public String getDataById(int id) {
        return "Real Data for ID: " + id;
    }

    public boolean saveData(String data) {
        // Simulates saving to external system
        return true;
    }
}
