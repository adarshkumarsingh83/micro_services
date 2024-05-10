package com.espark.adarsh.service;

import com.espark.adarsh.bean.Address;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

@Service
public class AddressService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public static final String ADDRESS_SERVICE = "addressService";



    public void getMessage(HashMap<String, String> response) {
        String serviceUrl = "http://ESPARK-ADDRESS-SERVICE/api/message";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("address-request", "ESPARK-HEADER-FROM-API-SERVICE");
        HttpEntity<String> customerHttpEntity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(serviceUrl, HttpMethod.GET, customerHttpEntity, String.class);
        response.put("ESPARK-ADDRESS-SERVICE", responseEntity.getBody());
    }


    @CircuitBreaker(name = ADDRESS_SERVICE,fallbackMethod = "getDefaultAddress")
    public Address getAddress(Long id) {
        return this.restTemplate.getForObject("http://ESPARK-ADDRESS-SERVICE/api/address/" + id, Address.class);
    }

    public Address getDefaultAddress(Long id) {
        return new Address(id, null, null, null);
    }


    @CircuitBreaker(name = ADDRESS_SERVICE,fallbackMethod = "getDefaultAddresses")
    public List<Address> getAddress() {
        List<Address> list = this.restTemplate.getForObject("http://ESPARK-ADDRESS-SERVICE/api/addresses", ArrayList.class);
        return this.typeSafe(list);
    }

    public List<Address> getDefaultAddresses() {
        return Arrays.asList();
    }

    private List<Address> typeSafe(List<Address> addresses) {
        try {
            String data = objectMapper.writeValueAsString(addresses);
            addresses = objectMapper.readValue(data, new TypeReference<List<Address>>() {
            });
        } catch (Exception e) {

        }
        return addresses;
    }
}
