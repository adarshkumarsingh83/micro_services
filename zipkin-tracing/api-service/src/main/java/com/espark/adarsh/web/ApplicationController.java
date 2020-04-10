package com.espark.adarsh.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApplicationController {

    @GetMapping("/server-message")
    public String getMessage() {
        log.info("lable=application-server getMessage()");
        return "welcome to the espark server";
    }
}
