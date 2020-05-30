package com.espark.adarsh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping(value = "/wish/{name}")
    public String getMessage(@PathVariable("name") String name){
        return "Welcome "+name;
    }

}
