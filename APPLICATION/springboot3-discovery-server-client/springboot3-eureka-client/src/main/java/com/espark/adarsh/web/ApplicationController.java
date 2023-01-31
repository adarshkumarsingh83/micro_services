package com.espark.adarsh.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;
    @Value("${spring.cloud.client.hostname}")
    String hostName;

    @Value("${application.message}")
    String message;

    @GetMapping("/wish/{name}")
    public Map<String, String> getWish(@PathVariable("name") String name) {
        return new HashMap<>() {
            {
                put("message", message+" " + name);
                put("hostname", hostName);
                put("instanceId", instanceId);
            }
        };
    }
}
