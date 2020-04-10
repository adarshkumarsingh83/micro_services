package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DataService {

    @Autowired
    RestTemplate restTemplate;


    public String getData(){
        log.info("label=data-service getData()");
        return restTemplate.getForObject("http://localhost:9090/server-message",String.class);
    }
}
