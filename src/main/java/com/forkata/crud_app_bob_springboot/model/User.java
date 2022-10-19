package com.forkata.crud_app_bob_springboot.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();


    public User() {
    }

    public User(String name, Integer age, String email, String username, String password, Set<Role> roles) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String name, Integer age, String email, String username, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String listOfRoles() {
        StringBuilder sb = new StringBuilder();


        roles.forEach(r -> {
            sb.append(r.name());
            sb.append(", ");
        });
        sb.delete(sb.length() - 2, sb.length() - 1);
        return sb.toString();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }


}
