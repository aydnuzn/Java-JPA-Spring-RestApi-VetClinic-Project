package com.works.properties;

import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Yeni Kullanıcı Ekleme Ara Katmanı" ,description = "Yeni kullanıcı eklemede kullanılır")
public class RegisterInterlayer {

    @ApiModelProperty(value = "Kullanıcı Adı", required = true, notes = "Kullanıcı Adı girilmezse işlem iptal edilir." ,dataType = Util.string,example = "Aydin")
    @NotEmpty(message = "user_name not empty!")
    @NotNull(message = "user_name not null!")
    String user_name;

    @ApiModelProperty(value = "Kullanıcı Soyadı", required = true, notes = "Kullanıcı Soyadı girilmezse işlem iptal edilir." ,dataType = Util.string,example = "Uzun")
    @NotEmpty(message = "user_surname not empty!")
    @NotNull(message = "user_surname not null!")
    String user_surname;

    @ApiModelProperty(value = "Kullanıcı Maili", required = true, notes = "Kullanıcı Maili girilmezse işlem iptal edilir." ,dataType = Util.string,example = "aydn@mail.com")
    @NotEmpty(message = "user_email not empty!")
    @NotNull(message = "user_email not null!")
    String user_email;

    @ApiModelProperty(value = "Kullanıcı Şifresi", required = true, notes = "Kullanıcı Şifresi girilmezse işlem iptal edilir." ,dataType = Util.string,example = "12345")
    @NotEmpty(message = "user_password not empty!")
    @NotNull(message = "user_password not null!")
    String user_password;

    @ApiModelProperty(value = "Kullanıcı Telefonu", required = true, notes = "Kullanıcı Telefonu girilmezse işlem iptal edilir." ,dataType = Util.string,example = "5125904355")
    @NotEmpty(message = "user_tel not empty!")
    @NotNull(message = "user_tel not null!")
    String user_tel;

    @ApiModelProperty(value = "Kullanıcı Rolleri", required = true, notes = "Kullanıcı Rolü girilmezse işlem iptal edilir." ,dataType = Util.string,example = "[1,2]")
    @NotEmpty(message = "not empty")
    String[] user_roles;

}
