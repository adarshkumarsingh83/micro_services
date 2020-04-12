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

public class UserIntegrationService {

    @Autowired
    RestTemplate restTemplate;


    @Value("${user.service.url}")
    String userServiceUrl;

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
}
