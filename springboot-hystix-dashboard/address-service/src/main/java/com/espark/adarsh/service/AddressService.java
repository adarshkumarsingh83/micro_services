package com.espark.adarsh.service;

import com.espark.adarsh.bean.Address;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddressService {


    private Map<String, Address> store = new HashMap<>();

    @PostConstruct
    public void init() {
        store.put("100", new Address("100", "111/0", "spring creek", "plano", "tx", "usa"));
        store.put("200", new Address("200", "222/1", "Town And country", "frisco", "tx", "usa"));
    }

    public Address getAddress(String userId) {

        if (!store.isEmpty()) {
            return store.containsKey(userId) ? store.get(userId) : null;
        } else {
            throw new RuntimeException("Address Not found");
        }

    }
}

