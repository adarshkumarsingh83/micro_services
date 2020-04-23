package com.espark.adarsh.service;

import com.espark.adarsh.integration.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApiService {

    @Autowired
    DataService dataService;

    public Map getMessage() {
        return this.dataService.getMessageData();
    }
}