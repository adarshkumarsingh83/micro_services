package com.espark.adarsh.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class Zone1Service {

    private final RestTemplate restTemplate;
    private final EurekaClient discoveryClient;
    private final String zoneVirtualAlias;


    public Zone1Service(final RestTemplate restTemplate,
                        final EurekaClient discoveryClient,
                        @Value("${espark.appconfig.zone2-service-alias}") final String zoneVirtualAlias) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.zoneVirtualAlias = zoneVirtualAlias;
    }

    @HystrixCommand(fallbackMethod = "zone2ServerMessageFallback")
    public Map<String, String> zone2ServerMessage() {
        final InstanceInfo instance = discoveryClient.getNextServerFromEureka(zoneVirtualAlias, false);
        Map<String, String> response = restTemplate.getForObject(instance.getHomePageUrl() + "zone2-server", HashMap.class);
        response.put("zone1-server", "welcome to the zone1 server ");
        return response;
    }

    public Map<String, String> zone2ServerMessageFallback() {
        return new HashMap<String, String>() {
            {
                put("zone2-server", "not reachable");
            }
        };
    }
}
