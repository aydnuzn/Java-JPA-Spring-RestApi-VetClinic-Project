package com.works.properties;

import com.works.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Randevu Ara Katman Modeli", description = "Randevu ekleme için kullanılır")
public class DiaryInterlayer {

    @ApiModelProperty(value = "Randevu Title", dataType = Util.string, required = true, example = "Toplantı")
    @NotEmpty(message = "diary_title not empty!")
    @NotNull(message = "diary_title not null!")
    private String diary_title;

    @ApiModelProperty(value = "Randevu Açıklaması", dataType = Util.string, required = true, example = "Spring Toplantısı")
    @NotEmpty(message = "diary_description not empty!")
    @NotNull(message = "diary_description not null!")
    private String diary_description;

    @ApiModelProperty(value = "Randevu Tarihi", dataType = Util.string, required = true, example = "2021-09-23")
    @NotEmpty(message = "diary_date not empty!")
    @NotNull(message = "diary_date not null!")
    private String diary_date;

    @ApiModelProperty(value = "Randevu Saati", dataType = Util.string, required = true, example = "18:54")
    @NotEmpty(message = "diary_time not empty!")
    @NotNull(message = "diary_time not null!")
    private String diary_time;

    private Boolean diary_statu;

    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "us_id not null!")
    private Integer us_id;

    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "cu_id not null!")
    private Integer cu_id;
}