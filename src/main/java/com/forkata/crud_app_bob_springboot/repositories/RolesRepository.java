package com.forkata.crud_app_bob_springboot.repositories;

import com.forkata.crud_app_bob_springboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role,Integer> {

}
