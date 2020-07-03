package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApplicationController {

    @GetMapping("/configuration/server")
    public String getServerConfiguration() {
        return new RestTemplate().getForObject("http://localhost:8888/configserver/dev", String.class);
    }

    @GetMapping("/configuration/client")
    public String getClientConfiguration() {
        return new RestTemplate().getForObject("http://localhost:8888/configclient/dev", String.class);
    }

}
