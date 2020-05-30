package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/message")
    public String employee(@RequestHeader(value = "employee-request", required = false) String header) {
        return "Hello from Espark Employee Service " + header;
    }

}