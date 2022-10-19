package com.forkata.crud_app_bob_springboot.controller;


import com.forkata.crud_app_bob_springboot.model.User;
import com.forkata.crud_app_bob_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;


@Controller
public class UsersController {

    private final UserService service;


    @Autowired
    public UsersController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user")
    public String userInfo(Principal principal, Model model) {
        model.addAttribute("user", service.getByUsername(principal.getName()));

        return "user";
    }

}
