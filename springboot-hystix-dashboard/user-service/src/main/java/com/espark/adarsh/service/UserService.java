package com.espark.adarsh.service;

import com.espark.adarsh.bean.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {


    private Map<String, User> store = new HashMap<>();

    @PostConstruct
    public void init() {
        store.put("100", new User("100","radha singh","radha@singh"));
        store.put("200", new User("200", "adarsh kumar","adarsh@kumar"));
    }

    public User getUser(String userId) {

        if (!store.isEmpty()) {
            return store.containsKey(userId) ? store.get(userId) : null;
        } else {
            throw new RuntimeException("User Not found");
        }

    }
}

