package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @GetMapping("/message")
    public String address(@RequestHeader(value = "address-request", required = false) String header) {
        return "Hello from Espark Address Service" + header;
    }

}