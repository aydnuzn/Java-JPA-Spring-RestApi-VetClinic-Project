package com.works.properties;

import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Şifre Değiştirme Ara Katman Modeli", description = "Şifre değiştirme için kullanılır.")
public class ChangePasswordInterlayer {

    @ApiModelProperty(value = "newpassword (4-10 characters)", notes = "Şifre değişirirken girilmesi gereklidir.", required = true, dataType = Util.string, example = "12345")
    @NotEmpty(message = "newpassword not empty!")
    @NotNull(message = "newpassword not null!")
    @Length(min = 4, max = 10, message = "newpassword en az 4 en fazla 10 karakter olabilir.")
    private String newpassword;

    @ApiModelProperty(value = "newpasswordr (4-10 characters)", notes = "Şifre değişirirken girilmesi gereklidir.", required = true, dataType = Util.string, example = "12345")
    @NotEmpty(message = "newpasswordr not empty!")
    @NotNull(message = "newpasswordr not null!")
    @Length(min = 4, max = 10, message = "newpasswordr en az 4 en fazla 10 karakter olabilir.")
    private String newpasswordr;

}
