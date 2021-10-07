package com.works.entities.security;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ForgotPasswordUser {
    @Id
    private String forgot_id;
    private String us_mail;
    private Boolean status;
}
