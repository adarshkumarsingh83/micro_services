package com.espark.adarsh.web;

import com.espark.adarsh.bean.Address;
import com.espark.adarsh.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/address/{id}")
    public Address getAddress(@PathVariable("id") String id) {
        return this.addressService.getAddress(id);
    }
}
