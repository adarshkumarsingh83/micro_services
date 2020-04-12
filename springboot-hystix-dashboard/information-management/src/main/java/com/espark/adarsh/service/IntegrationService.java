package com.espark.adarsh.service;

import com.espark.adarsh.bean.Address;
import com.espark.adarsh.bean.ApiBean;
import com.espark.adarsh.bean.User;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.net.URI;

@Slf4j
@Service

public class IntegrationService {

    @Autowired
    RestTemplate restTemplate;


    @Value("${address.service.url}")
    String addressServiceUrl;

    @Value("${user.service.url}")
    String userServiceUrl;

    public ApiBean getData(String id) {
        log.info("label=information-service getData() ={}", id);
        User user = getUser(id);
        Address address = getAddress(id);
        return new ApiBean(id, address, user);
    }

    @HystrixCommand(fallbackMethod = "getDefaultUser")
    public User getUser(String id) {
        log.info("label=information-service getUser()  User Service ={}", id);
        URI uri = URI.create(userServiceUrl + id);
        User user = this.restTemplate.getForObject(uri, User.class);
        return user;
    }

    @SuppressWarnings("unused")
    public User getDefaultUser(String id) {
        log.info("label=information-service getDefaultUser() " +
                "Hystrix Circuit Opened for User Service id={}", id);
        return new User();
    }

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
