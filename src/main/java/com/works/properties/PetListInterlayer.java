package com.works.properties;

import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@ApiModel(value = "Pet Ara Katman Modeli", description = "Pet ekleme için kullanılır.")
public class PetListInterlayer {
    @ApiModelProperty(value = "Hayvan Adı", required = true, notes = "Hayvan ismi girilmezse işlemler iptal edilir.", example = "Pamuk")
    @NotNull(message = "name NULL OLAMAZ")
    @NotEmpty(message = "name EMPTY OLAMAZ")
    private String name;

    @ApiModelProperty(value = "Çip Numarası", required = true, notes = "Çip numarası girilmezse işlemler iptal edilir.", example = "1")
    @NotNull(message = "chipNumber NULL OLAMAZ")
    @NotEmpty(message = "chipNumber EMPTY OLAMAZ")
    private String chipNumber;

    @ApiModelProperty(value = "Karne / Küpe Numarası", required = true, notes = "Karne ya da Küpe numarası girilmezse işlemler iptal edilir.", example = "1")
    @NotNull(message = "earTag NULL OLAMAZ")
    @NotEmpty(message = "earTag EMPTY OLAMAZ")
    private String earTag;

    @ApiModelProperty(value = "Doğum Tarihi", dataType = Util.date,required = true, example = "2021-10-01T09:59:01.711+00:00")
    @NotNull(message = "bornDate NULL OLAMAZ")
    private Date bornDate;

    @ApiModelProperty(value = "Tür", required = true, notes = "Tür girilmezse işlemler iptal edilir.", dataType = Util.integer, example = "1")
    @NotNull(message = "type NULL OLAMAZ")
    @NotEmpty(message = "type EMPTY OLAMAZ")
    private String type;

    @ApiModelProperty(value = "Kısır / Kısır Değil", required = true, notes = "Kısırlık bilgisi girilmezse işlemler iptal edilir.", dataType = Util.myBoolean, example = "true")
    @NotNull(message = "neutering NULL OLAMAZ")
    @NotEmpty(message = "neutering EMPTY OLAMAZ")
    private String neutering;

    @ApiModelProperty(value = "Irk", required = true, notes = "Irk girilmezse işlemler iptal edilir.", dataType = Util.integer, example = "1")
    @NotNull(message = "breed NULL OLAMAZ")
    @NotEmpty(message = "breed EMPTY OLAMAZ")
    private String breed;

    @ApiModelProperty(value = "Renk", required = true, notes = "Renk girilmezse işlemler iptal edilir.", dataType = Util.integer, example = "1")
    @NotNull(message = "color NULL OLAMAZ")
    @NotEmpty(message = "color EMPTY OLAMAZ")
    private String color;

    @ApiModelProperty(value = "Cinsiyet", required = true, notes = "Cinsiyet girilmezse işlemler iptal edilir.", dataType = Util.myBoolean, example = "true")
    @NotNull(message = "gender NULL OLAMAZ")
    @NotEmpty(message = "gender EMPTY OLAMAZ")
    private String gender;
}