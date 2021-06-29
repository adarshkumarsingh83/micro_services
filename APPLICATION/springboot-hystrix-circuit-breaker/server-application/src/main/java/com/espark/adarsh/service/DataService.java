package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DataService {

    public Map<String, Object> getNames() {
        log.info("label=data-service data fetch method execution ");
        return new HashMap<String, Object>() {
            {
                put("msg", "response from remote server");
                put("data", Arrays.asList(new String[]{"adarsh", "radha", "amit"}));
            }
        };
    }
}
