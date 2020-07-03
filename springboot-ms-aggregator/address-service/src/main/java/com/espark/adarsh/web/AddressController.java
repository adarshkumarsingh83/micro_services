package com.espark.adarsh.web;

import com.espark.adarsh.entity.Address;
import com.espark.adarsh.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {


    @Autowired
    AddressService addressService;

    @GetMapping("/message")
    public String address(@RequestHeader(value = "address-request", required = false) String header) {
        return "Hello from Espark Address Service" + header;
    }

    @GetMapping("/address/{id}")
    public Address addressRepository(@PathVariable("id") Long id) {
        return addressService.getById(id);
    }

    @GetMapping("/addresses")
    public List<Address> getAddress() {
       return addressService.getAddress();
    }

}