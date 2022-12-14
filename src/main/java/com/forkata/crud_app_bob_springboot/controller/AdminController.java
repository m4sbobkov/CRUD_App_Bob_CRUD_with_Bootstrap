package com.forkata.crud_app_bob_springboot.controller;


import com.forkata.crud_app_bob_springboot.model.Role;
import com.forkata.crud_app_bob_springboot.model.User;
import com.forkata.crud_app_bob_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService service;


    @Autowired
    public AdminController(UserService service) {
        this.service = service;

    }

    @GetMapping("/users")
    public String showAllUsers(Principal principal, Model model,@ModelAttribute("user") User user) {
        model.addAttribute("users", service.listUsers());
        model.addAttribute("roles", Role.values());
        model.addAttribute("currentUser", (User)service.loadUserByUsername(principal.getName()));
        return "users";
    }


    @PostMapping
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam Set<Role> roles) {
        user.setRoles(roles);
        service.create(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public User getUser(@PathVariable("id") Long id) {
        return service.userById(id);
    }


    @PatchMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam Set<Role> roles) {
        user.setRoles(roles);
        service.save(user);
        return "redirect:/admin/users";
    }


    @DeleteMapping("/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        service.delete(user.getId());
        return "redirect:/admin/users";
    }


}
