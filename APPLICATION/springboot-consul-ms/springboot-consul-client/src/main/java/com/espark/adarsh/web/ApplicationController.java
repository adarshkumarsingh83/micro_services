package com.espark.adarsh.web;

import com.espark.adarsh.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    ApiService apiService;

    @GetMapping("/client/massage")
    public Map getMessage() {
        return this.apiService.getMessage();
    }
}