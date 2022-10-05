package com.forkata.crud_app_bob_springboot.service;


import com.forkata.crud_app_bob_springboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> listUsers();

    User userById(Long id);

    void save(User user);

    void delete(Long id);

    void create(User user);



}
