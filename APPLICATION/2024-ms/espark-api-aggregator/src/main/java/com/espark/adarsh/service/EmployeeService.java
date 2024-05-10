package com.espark.adarsh.service;

import com.espark.adarsh.bean.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public static final String EMPLOYEE_SERVICE = "employeeService";


    public void getMessage(HashMap<String,String> response) {
        String serviceUrl = "http://ESPARK-EMPLOYEE-SERVICE/api/message";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("employee-request", "ESPARK-HEADER-FROM-API-SERVICE");
        HttpEntity<String> customerHttpEntity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.GET, customerHttpEntity, String.class);
        response.put("ESPARK-EMPLOYEE-SERVICE", responseEntity.getBody());
    }

    @CircuitBreaker(name = EMPLOYEE_SERVICE,fallbackMethod = "getDefaultEmployee")
    public Employee getEmployee(Long id) {
        return this.restTemplate.getForObject("http://ESPARK-EMPLOYEE-SERVICE/api/employee/" + id, Employee.class);
    }

    public Employee getDefaultEmployee(Long id) {
        return new Employee(id, null, null, null);
    }

    @CircuitBreaker(name = EMPLOYEE_SERVICE,fallbackMethod = "getDefaultEmployees")
    public List<Employee> getEmployee() {
        List<Employee> employees = this.restTemplate.getForObject("http://ESPARK-EMPLOYEE-SERVICE/api/employees", ArrayList.class);
        return typeSafe(employees);
    }

    public List<Employee> getDefaultEmployees() {
        return Arrays.asList();
    }

    private List<Employee> typeSafe(List<Employee> employees) {
        try {
            String data = objectMapper.writeValueAsString(employees);
            employees = objectMapper.readValue(data, new TypeReference<List<Employee>>() {
            });
        } catch (Exception e) {

        }
        return employees;
    }
}

