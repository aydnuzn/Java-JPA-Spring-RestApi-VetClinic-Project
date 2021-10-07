package com.works.properties;

import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Kullanıcı Rol Değiştirme Ara Katmanı", description = "Mevcut kullanıcı rollerini değiştirmede kullanılır")
public class RegisterChangeInterlayer {

    @ApiModelProperty(value = "Kullanıcı Adı", required = true, notes = "Kullanıcı Adı girilmezse işlem iptal edilir.", dataType = Util.integer, example = "1")
    @NotEmpty(message = "user_name not empty!")
    @NotNull(message = "user_name not null!")
    String change_user_name;

    @ApiModelProperty(value = "Kullanıcı Rolleri", required = true, notes = "Kullanıcı Rolü girilmezse işlem iptal edilir.", dataType = Util.string, example = "[1,2]")
    @NotEmpty(message = "not empty")
    String[] change_user_roles;

}
