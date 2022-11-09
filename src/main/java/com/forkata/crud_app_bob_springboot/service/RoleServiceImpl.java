package com.forkata.crud_app_bob_springboot.service;

import com.forkata.crud_app_bob_springboot.model.Role;
import com.forkata.crud_app_bob_springboot.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RoleServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }


    @Override
    public List<Role> findAll() {
        return rolesRepository.findAll();
    }

    @Override
    public void save(Role role) {
        rolesRepository.save(role);
    }
}
