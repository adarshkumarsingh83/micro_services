package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApplicationController {

    @GetMapping("/message")
    public Map getMessage() {
        return new HashMap() {
            {
                put("msg", "welcome to the espark");
                put("type", "zookeeper client server");
            }
        };
    }
}
