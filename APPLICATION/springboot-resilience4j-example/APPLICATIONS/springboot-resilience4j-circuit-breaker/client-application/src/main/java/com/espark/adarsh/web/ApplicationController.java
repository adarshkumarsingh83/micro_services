package com.espark.adarsh.web;

import com.espark.adarsh.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class ApplicationController {

    @Autowired
    DataService dataService;

    @GetMapping("/name-list")
    public HashMap<String,Object> getName(){
        log.info("label=application-controller data fetch method execution ");
        return dataService.getNameList();
    }
}
