package com.forkata.crud_app_bob_springboot.repositories;


import com.forkata.crud_app_bob_springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    @Query("select p from User p join fetch p.roles where p.username = :username")
    Optional<User> findByUsername(String username);

}
