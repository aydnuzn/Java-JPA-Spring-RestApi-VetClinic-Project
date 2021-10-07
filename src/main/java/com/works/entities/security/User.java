package com.works.entities.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.works.entities.listener.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer us_id;

    private String us_name;
    private String us_surname;
    @Column(unique = true)
    private String email;
    private String password;
    private String us_tel;
    private String user_file;

    private boolean enabled;//aktif - pasif
    private boolean tokenExpired;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "us_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "ro_id"))
    private List<Role> roles;


}
