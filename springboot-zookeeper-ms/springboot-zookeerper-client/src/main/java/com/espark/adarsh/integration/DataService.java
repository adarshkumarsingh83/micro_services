package com.espark.adarsh.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Service
@EnableFeignClients
public class DataService {

    @Autowired
    DataServerService dataServerService;

    @FeignClient(name = "zookeeper-server")
    interface DataServerService {
        @RequestMapping(value = "/message", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
        @ResponseBody
        Map getMessage();
    }

    public Map getMessageData() {
        return this.dataServerService.getMessage();
    }

}
