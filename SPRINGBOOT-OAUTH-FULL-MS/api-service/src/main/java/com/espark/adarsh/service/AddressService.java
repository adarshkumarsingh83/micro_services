package com.espark.adarsh.service;

import com.espark.adarsh.bean.Address;
import com.espark.adarsh.util.ApplicationUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class AddressService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;


    public void getMessage(HashMap<String, String> response) {
        log.info("label=address-service getMessage() executing");
        String serviceUrl = "http://ADDRESS-SERVICE/api/message";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", ApplicationUtil.getAccessToken());
        httpHeaders.add("address-request", "ESPARK-HEADER-FROM-API-SERVICE");
        HttpEntity<String> customerHttpEntity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.GET, customerHttpEntity, String.class);
        response.put("ESPARK-ADDRESS-SERVICE", responseEntity.getBody());
    }

    @HystrixCommand(fallbackMethod = "getDefaultAddress")
    public Address getAddress(Long id) {
        log.info("label=address-service getAddress() executing");
        String serviceUrl = "http://ADDRESS-SERVICE/api/address/" + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", ApplicationUtil.getAccessToken());
        httpHeaders.add("address-request", "ESPARK-HEADER-FROM-API-SERVICE");
        HttpEntity<String> customerHttpEntity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<Address> responseEntity = this.restTemplate.exchange(serviceUrl, HttpMethod.GET, customerHttpEntity, Address.class);
        return responseEntity.getBody();
    }

    public Address getDefaultAddress(Long id) {
        log.info("label=address-service getDefaultAddress() executing");
        return new Address(id, null, null, null);
    }

    @HystrixCommand(fallbackMethod = "getDefaultAddresses")
    public List<Address> getAddress() {
        log.info("label=address-service getAddress() executing");
        String serviceUrl = "http://ADDRESS-SERVICE/api/addresses";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", ApplicationUtil.getAccessToken());
        httpHeaders.add("address-request", "ESPARK-HEADER-FROM-API-SERVICE");
        HttpEntity<String> customerHttpEntity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<Address[]> responseEntity = this.restTemplate.exchange(serviceUrl, HttpMethod.GET, customerHttpEntity, Address[].class);
        return this.typeSafe(Arrays.asList(responseEntity.getBody()));
    }

    public List<Address> getDefaultAddresses() {
        log.info("label=address-service getDefaultAddresses() executing");
        return Arrays.asList();
    }

    private List<Address> typeSafe(List<Address> addresses) {
        log.info("label=address-service typeSafe() executing");
        try {
            String data = objectMapper.writeValueAsString(addresses);
            addresses = objectMapper.readValue(data, new TypeReference<List<Address>>() {
            });
        } catch (Exception e) {

        }
        return addresses;
    }
}
