package com.forkata.crud_app_bob_springboot.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();


    public User() {
    }

    public User(String name, Integer age, String email, String username, String password, List<Role> roles) {
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
            sb.append(r.getName());
            sb.append(", ");
        });
        sb.delete(sb.length() - 2, sb.length() - 1);
        return sb.toString();
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }


}
