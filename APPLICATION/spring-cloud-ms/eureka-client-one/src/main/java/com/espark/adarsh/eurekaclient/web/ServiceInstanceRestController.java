package com.espark.adarsh.eurekaclient.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ServiceInstanceRestController {

    @Value("${spring.application.name}")
    String serviceName;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }


    @GetMapping("/service-one/message")
    public String serviceOne(@RequestHeader(name = "X-ONE-HEADER", required = false) String headerValue) {
        return "RESPONSE FROM " + serviceName.toUpperCase() + " CLIENT =>" + headerValue;
    }

    @GetMapping("/service-one/configuration/{profile}")
    public String getClientConfiguration(@PathVariable(name = "profile", required = false) String profile) {
        return new RestTemplate().getForObject("http://localhost:8888/eureka-client-one" + (StringUtils.isEmpty(profile) ? "" : "/" + profile.toLowerCase()), String.class);
    }

}
