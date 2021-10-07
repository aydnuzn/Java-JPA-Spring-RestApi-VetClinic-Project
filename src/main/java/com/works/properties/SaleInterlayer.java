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
@ApiModel(value = "Satış Ara Katman Modeli", description = "Satış Ekleme ve Listeleme İçin Kullanılır.")
public class SaleInterlayer {

    @ApiModelProperty(value = "Müşteri IDsi", required = true, notes = "Müşteri id si girilmezse hata verir.", dataType = Util.integer, example = "1")
    private Integer cid;

    @ApiModelProperty(value = "Ürün IDsi", required = true, notes = "Ürün id si girilmezse hata verir.", dataType = Util.integer, example = "1")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "pid not null!")
    private Integer pid;

    @ApiModelProperty(value = "Ürün Adeti", required = true, notes = "Ürün adeti girilmezse hata verir.",dataType = Util.integer,  example = "1")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "pAmount not null!")
    private Integer pAmount;

    @ApiModelProperty(value = "Ödeme Türü", required = true, notes = "Ödeme türü girilmezse hata verir.", dataType = Util.integer, example = "1")
    @Max(value = 3, message = "3 çeşit ödeme yöntemi olabilir")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "pPaymentType not null!")
    private Integer pPaymentType;

    @ApiModelProperty(value = "Satış Notu", required = true, notes = "Müşteri notu girilmezse hata verir.", example = "1")
    @NotEmpty(message = "pNote not empty!")
    @NotNull(message = "pNote not null!")
    private String pNote;
}