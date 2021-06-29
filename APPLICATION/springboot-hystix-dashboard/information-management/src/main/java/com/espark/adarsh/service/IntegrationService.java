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
    UserIntegrationService userIntegrationService;

    @Autowired
    AddressIntegrationService addressIntegrationService;

    public ApiBean getData(String id) {
        log.info("label=information-service getData() ={}", id);
        User user = userIntegrationService.getUser(id);
        Address address = addressIntegrationService.getAddress(id);
        return new ApiBean(id, address, user);
    }

}
