package com.espark.adarsh.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataService {

    @Value("${espark.message}")
    private String message;

    @Value("${espark.name}")
    private String name;

    @Value("${espark.email}")
    private String email;

    public Map<String, String> getData() {
        return new HashMap<String, String>() {
            {
                put("ESPARK-NAME", name);
                put("ESPARK-MESSAGE", message);
                put("ESPARK-EMAIL", email);
            }
        };
    }

}
