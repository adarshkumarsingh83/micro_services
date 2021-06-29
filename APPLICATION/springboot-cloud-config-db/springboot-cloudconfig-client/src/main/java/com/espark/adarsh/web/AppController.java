package com.espark.adarsh.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api")
public class AppController {


    @Value("${user-name}")
    private String name;

    @RequestMapping("/config-values")
    public String getConfigServerProps() {
        return "Fetched [" + name + "] from config server";
    }
}
