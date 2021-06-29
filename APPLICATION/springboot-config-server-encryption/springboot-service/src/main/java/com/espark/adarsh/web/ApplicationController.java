package com.espark.adarsh.web;

import com.espark.adarsh.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RefreshScope
@RestController
@RequestMapping("/api")
public class ApplicationController {


    @Autowired
    private DataService dataService;

    @RequestMapping("/data")
    public Map<String, String> getData() {
        return this.dataService.getData();
    }
}
