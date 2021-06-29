package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.net.URI;

@Slf4j
@Service
@EnableCircuitBreaker
public class DataService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "localExecution")
    public Map<String,Object> getNameList(){
        log.info("label=data-server data fetch method execution ");
        URI uri = URI.create("http://localhost:9090/list");
        return this.restTemplate.getForObject(uri, HashMap.class);
    }


    private Map<String,Object> localExecution(){
        log.info("label=data-server hystrix fall back method execution ");
        return new HashMap<String,Object>(){
            {
                put("msg","response from local server");
                put("data",Arrays.asList(new String[]{"sonu","radha","monu"}));
            }
        };
    }
}
