package com.espark.adarsh.web;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/message")
    public String employee(@RequestHeader(value = "employee-request", required = false) String header) {
        return "Hello from Espark Employee Service " + header;
    }


    @GetMapping("/employee/{id}")
    public Employee getById(@PathVariable("id") Long id) {
        return this.employeeService.getById(id);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return this.employeeService.getAllEmployee();
    }

}