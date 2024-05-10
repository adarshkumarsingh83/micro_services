package com.espark.adarsh.web;

import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AggregatorController {

    @Value("${application.message:'xxx'}")
    String message;

    @GetMapping({"/wish", "/greet"})
    public String greet() {
        return message+" AggregatorController";
    }

    @Autowired
    ApiService apiService;

    @GetMapping("/message")
    public Map<String, String> message(@RequestHeader(value = "api-request", required = false) String header) {
        Map<String, String> response = this.apiService.getMessages();
        response.put("ESPARK-API-SERVICES", "Hello from Espark Api Service " + header);
        return response;
    }

    @GetMapping("/address/{id}")
    public ResponseBean getAddress(@PathVariable("id") Long id) {
        return this.apiService.getAddress(id);
    }

    @GetMapping("/employee/{id}")
    public ResponseBean getEmployee(@PathVariable("id") Long id) {
        return this.apiService.getEmployee(id);
    }

    @GetMapping("/details/{id}")
    public ResponseBean getData(@PathVariable("id") Long id) {
        return this.apiService.getData(id);
    }

    @GetMapping("/details")
    public List<ResponseBean> getAllData() {
        return this.apiService.getAllData();
    }
}
