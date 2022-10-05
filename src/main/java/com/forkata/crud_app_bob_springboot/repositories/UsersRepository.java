package com.forkata.crud_app_bob_springboot.repositories;


import com.forkata.crud_app_bob_springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
