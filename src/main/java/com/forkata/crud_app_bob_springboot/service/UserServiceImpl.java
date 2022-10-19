package com.forkata.crud_app_bob_springboot.service;


import com.forkata.crud_app_bob_springboot.model.Role;
import com.forkata.crud_app_bob_springboot.model.User;
import com.forkata.crud_app_bob_springboot.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UsersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<User> listUsers() {
        return repository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return repository.findByUsername(username).orElseThrow();
    }

    @Override

    public User userById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void save(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User updatedUser = repository.findById(user.getId()).get();

        updatedUser.setName(user.getName());
        updatedUser.setAge(user.getAge());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setRoles(user.getRoles());

        repository.save(updatedUser);
    }

    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles().isEmpty()) {
            user.addRole(Role.USER);
        }
        repository.save(user);

    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
    }


}
