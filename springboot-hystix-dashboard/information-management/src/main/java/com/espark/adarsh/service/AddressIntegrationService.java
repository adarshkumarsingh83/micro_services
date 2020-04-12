package com.espark.adarsh.service;

import com.espark.adarsh.bean.Address;
import com.espark.adarsh.bean.ApiBean;
import com.espark.adarsh.bean.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@Service
public class AddressIntegrationService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${address.service.url}")
    String addressServiceUrl;

    @HystrixCommand(fallbackMethod = "getDefaultAddress")
    public Address getAddress(String id) {
        log.info("label=information-service getAddress()  Address Service ={}", id);
        URI uri = URI.create(addressServiceUrl + id);
        Address address = this.restTemplate.getForObject(uri, Address.class);
        return address;
    }

    @SuppressWarnings("unused")
    public Address getDefaultAddress(String id) {
        log.info("label=information-service getDefaultAddress() " +
                "Hystrix Circuit Opened for Address Service id={}", id);
        return new Address();
    }
}
