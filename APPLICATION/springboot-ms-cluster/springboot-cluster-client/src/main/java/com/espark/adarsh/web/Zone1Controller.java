package com.espark.adarsh.web;

import com.espark.adarsh.service.Zone1Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Profile("zone1")
public class Zone1Controller {

    @Autowired
    Zone1Service zone1Service;


    @GetMapping("/zone1-server")
    public Map<String, String> zone1Server() {
        log.info("label=Zone1Controller zone1Server()");
        return new HashMap<String, String>() {
            {
                put("zone1-server", "welcome to the zone1 server ");
            }
        };
    }

    @GetMapping("/zone1-server-wish")
    public Map<String, String> zone1ServerWish() {
        log.info("label=Zone1Controller zone1ServerWish()");
        return this.zone1Service.zone2ServerMessage();
    }
}

