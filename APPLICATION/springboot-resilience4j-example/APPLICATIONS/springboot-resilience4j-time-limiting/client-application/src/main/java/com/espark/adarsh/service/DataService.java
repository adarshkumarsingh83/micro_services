package com.espark.adarsh.service;

import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class DataService {

    @Lazy
    @Autowired
    RestTemplate restTemplate;

    public static final String DATA_SERVICE = "dataService";

    @TimeLimiter(name = DATA_SERVICE, fallbackMethod = "localExecution")
    public CompletableFuture<HashMap<String, Object>> getNameList() {
        log.info("label=data-server data fetch method execution ");
        URI uri = URI.create("http://localhost:9090/list");
        return CompletableFuture.supplyAsync(() -> this.restTemplate.getForObject(uri, HashMap.class));
    }


    public CompletableFuture<HashMap<String, Object>> localExecution(Exception e) {
        log.info("label=data-server resilience4j fall back method execution ");
        return CompletableFuture.supplyAsync(() -> new HashMap<String, Object>() {
            {
                put("msg", "response from local server :=> remote is not responding on time ");
                put("data", Arrays.asList(new String[]{"sonu", "radha", "monu"}));
            }
        });
    }
}
