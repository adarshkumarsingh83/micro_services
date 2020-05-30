package com.espark.adarsh.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// This will allow us to reinitialize this controller to get any new config
// values when the /refresh endpoint is POSTed to.
@RefreshScope
@RequestMapping("/api")
public class AppController {

    @Value("${info.foo}")
    private String fooProperty;

    @Value("${espark.adarsh.name}")
    private String[] names;

    @RequestMapping("/value")
    public String getConfigurationsValues() {
        return "Using [" + fooProperty + "] from config server";
    }
}
