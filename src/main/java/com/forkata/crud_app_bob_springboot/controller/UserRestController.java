package com.forkata.crud_app_bob_springboot.controller;


import com.forkata.crud_app_bob_springboot.model.User;
import com.forkata.crud_app_bob_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<User> getUser(Principal principal){
        return new ResponseEntity<>(service.getByUsername(principal.getName()), HttpStatus.OK);
    }



}
