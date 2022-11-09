package com.forkata.crud_app_bob_springboot.service;

import com.forkata.crud_app_bob_springboot.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    List<Role> findAll();

    void save(Role role);


}
