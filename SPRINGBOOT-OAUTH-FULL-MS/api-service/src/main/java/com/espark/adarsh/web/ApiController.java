package com.espark.adarsh.web;

import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class ApiController {

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