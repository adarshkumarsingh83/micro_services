package com.espark.adarsh.api.server.web.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ConfigController {

    @Autowired
    Environment environment;

    @Value("${user-name:''}")
    String userName;


    @GetMapping("/config/{prop-name}")
    public String getConfig(@PathVariable("prop-name") String propName) {
        if (environment.containsProperty(propName)) {
            return "CONFIG FROM CONFIG SERVER " + this.environment.getProperty(propName);
        } else {
            return "SUPPLIED PROPERTIES NOT FOUND IN CONFIGURATION ";
        }
    }

}
