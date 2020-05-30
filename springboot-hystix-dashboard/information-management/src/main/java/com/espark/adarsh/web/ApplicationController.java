package com.espark.adarsh.web;

import com.espark.adarsh.bean.ApiBean;
import com.espark.adarsh.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    IntegrationService integrationService;

    @GetMapping("/information/{id}")
    public ApiBean getInformation(@PathVariable("id") String id) {
        return this.integrationService.getData(id);
    }
}
