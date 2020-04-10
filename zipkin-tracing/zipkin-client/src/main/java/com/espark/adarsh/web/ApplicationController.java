package com.espark.adarsh.web;

import com.espark.adarsh.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationController {

    @Autowired
    DataService dataService;

    @GetMapping("/message")
    public String getMessageFromServer() {
        log.info("label=application-controller getMessageFromServer()");
        return this.dataService.getData();
    }
}
