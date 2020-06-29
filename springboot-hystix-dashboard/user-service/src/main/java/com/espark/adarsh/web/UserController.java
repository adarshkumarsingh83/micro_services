package com.espark.adarsh.web;

import com.espark.adarsh.bean.User;
import com.espark.adarsh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") String id) {
        return this.userService.getUser(id);
    }
}
