package com.espark.adarsh.web;

import com.espark.adarsh.service.Zone2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@Profile("zone2")
public class Zone2Controller {

    @Autowired
    Zone2Service zone2Service;

    @GetMapping("/zone2-server")
    public Map<String,String> zone2Server() {
        log.info("label=Zone2Controller zone2Server()");
        return new HashMap<String,String>(){
            {
                put("zone2-server","welcome to the zone2 server ");
            }
        };
    }

    @GetMapping("/zone2-server-wish")
    public Map<String, String> zone1ServerWish() {
        log.info("label=Zone1Controller zone1ServerWish()");
        return this.zone2Service.zone1ServerMessage();
    }

}
