package com.espark.adarsh.api.server.service;


import com.espark.adarsh.api.server.bean.LoginBean;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.espark.adarsh.api.server.ApiServerApplicationTests;
import com.espark.adarsh.api.server.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
public class AuthenticationServiceTest extends ApiServerApplicationTests {

    List<LoginBean> loginBeanList = null;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        loginBeanList = objectMapper.readValue(new File("src/test/resources/json-store/login.json"),
                new TypeReference<List<LoginBean>>() {
                });
        log.info("label=AuthenticationServiceTest init() dataLoad={}", loginBeanList);
    }

    @BeforeEach
    public void initEach() {
        log.info("label=AuthenticationServiceTest initEach() method called");
        //TODO INIT ACTIVITY FOR EACH THE TEST CASES
    }


    @Test
    public void testLogin() {
        LoginBean loginBean = loginBeanList.get(0);
        Object response = this.authenticationService.loginProcess(loginBean);
        if (response instanceof Map) {
            Map<String, String> serviceRes = (Map<String, String>) response;
            log.info("dummy test response {}", serviceRes);
            Assertions.assertNotNull(serviceRes);
        }
    }

    @Test
    public void testLoginFailure() {
        Assertions.assertThrows(ValidationException.class, () -> {
            LoginBean loginBean = loginBeanList.get(1);
            Object response = this.authenticationService.loginProcess(loginBean);
            if (response instanceof Map) {
                Map<String, String> serviceRes = (Map<String, String>) response;
                log.info("dummy test response {}", serviceRes);
            }
        });
    }


    @AfterEach
    public void cleanUpEach() {
        log.info("label=AuthenticationServiceTest cleanUpEach() method called");
        //TODO CLEANUP ACTIVITY FOR EACH THE TEST CASES
    }

}
