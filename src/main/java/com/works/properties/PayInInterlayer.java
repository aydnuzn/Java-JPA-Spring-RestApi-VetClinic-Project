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
@ApiModel(value = "Kasa Para Girişi Ara Katman Modeli", description = "Kasaya Para Girişinde Kullanılmaktadır..")
public class PayInInterlayer {

    @ApiModelProperty(value = "Müşteri id", required = true, notes = "Müşteri id si girilmezse hata olur.", dataType = Util.integer, example = "1")
    private Integer cu_id;

    @ApiModelProperty(value = "Detay", dataType = Util.string, required = true, example = "aşı para ödemesi")
    @NotEmpty(message = "pin_detail not empty!")
    @NotNull(message = "pin_detail not null!")
    private String pin_detail;

    @ApiModelProperty(value = "Ödeme Türü", dataType = Util.integer, required = true, example = "1")
    @Min(value = 1, message = "Ödeme yöntemi seçiniz")
    @Max(value = 3, message = "3 çeşit ödeme yöntemi olabilir")
    @NotNull(message = "pin_paymentType not null!")
    private Integer pin_paymentType;

    @ApiModelProperty(value = "Para Miktarı", dataType = Util.integer, required = true, example = "400")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "pin_price not null!")
    private Integer pin_price;
}
