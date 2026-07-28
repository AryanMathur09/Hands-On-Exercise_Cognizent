package com.tdd;

// Service that DEPENDS on ExternalApi
// This is what we want to TEST
public class MyService {

    private ExternalApi externalApi;

    // Dependency injected via constructor
    // This makes it MOCKABLE!
    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String fetchDataById(int id) {
        return externalApi.getDataById(id);
    }

    public boolean processData(String data) {
        if (data == null || data.isEmpty()) {
            return false;
        }
        return externalApi.saveData(data);
    }
}
