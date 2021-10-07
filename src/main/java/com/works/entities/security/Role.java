package com.works.entities.security;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Api(value = "Diary Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ro_id;
    private String ro_name;


    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private List<User> users;


}
