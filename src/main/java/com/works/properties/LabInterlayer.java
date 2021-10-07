package com.works.properties;

import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Lab Ara Katman Modeli", description = "Laboratuvar Ekleme ve Listeleme İçin Kullanılır.")
public class LabInterlayer {

    @ApiModelProperty(value = "Lab tipi", required = true, dataType = Util.integer, example = "1")
    @Max(value = 3, message = "3 çeşit laboratuvar türü olabilir")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "lab_type not null!")
    private Integer lab_type;

    @ApiModelProperty(value = "Lab detayı", required = true, dataType = Util.string, example = "detay")
    @NotEmpty(message = "lab_detail not empty!")
    @NotNull(message = "lab_detail not null!")
    private String lab_detail;

    @ApiModelProperty(value = "Pet ve customer ara tablosu idsi", required = true, dataType = Util.integer, example = "1")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "cu_id not null!")
    private Integer jpt_id;//pet ve customer ara tablosu

    @ApiModelProperty(value = "Doktor idsi", required = true, dataType = Util.integer, example = "1")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "us_id not null!")
    private Integer us_id;//doktor

}
