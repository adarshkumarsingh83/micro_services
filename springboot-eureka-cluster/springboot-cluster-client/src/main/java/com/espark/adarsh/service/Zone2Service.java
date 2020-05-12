package com.espark.adarsh.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class Zone2Service {


    private final RestTemplate restTemplate;
    private final EurekaClient discoveryClient;
    private final String zoneVirtualAlias;


    public Zone2Service(final RestTemplate restTemplate,
                        final EurekaClient discoveryClient,
                        @Value("${espark.appconfig.zone1-service-alias}") final String zoneVirtualAlias) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.zoneVirtualAlias = zoneVirtualAlias;
    }

    @HystrixCommand(fallbackMethod = "zone1ServerMessageFallback")
    public Map<String, String> zone1ServerMessage() {
        final InstanceInfo instance = discoveryClient.getNextServerFromEureka(zoneVirtualAlias, false);
        Map<String, String> response = restTemplate.getForObject(instance.getHomePageUrl() + "zone1-server", HashMap.class);
        response.put("zone2-server", "welcome to the zone2 server ");
        return response;
    }


    public Map<String, String> zone1ServerMessageFallback() {
        return new HashMap<String, String>() {
            {
                put("zone1-server", "not reachable");
            }
        };
    }

}
