package com.works.entities;

import com.works.entities.security.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer diary_id;

    private String diary_title;
    private String diary_description;

    private String diary_date;
    private String diary_time;
    private Boolean diary_statu;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "us_id")
    User user;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cu_id")
    Customer customer;
}