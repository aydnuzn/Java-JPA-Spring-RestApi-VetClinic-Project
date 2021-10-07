package com.works.entities;

import com.works.entities.listener.BaseEntity;
import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Kasa Para Çıkış Modeli", description = "Kasadan Para Alınırken Kullanılmaktadır..")
public class PaymentOut extends BaseEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer pout_id;

    @ApiModelProperty(value = "Detay", dataType = Util.string, example = "aylık fatura ödemesi")
    @NotEmpty(message = "Detay kısmı boş olamaz!")
    @NotNull(message = "Detay kısmı null olamaz!")
    private String pout_detail;

    @ApiModelProperty(value = "Para Miktarı", dataType = Util.integer, example = "100")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "pout_price null olamaz!")
    private Integer pout_price;

    @ApiModelProperty(value = "Ödeme İşlemi Tipi", dataType = Util.integer, example = "1")
    @Min(value = 1, message = "Ödeme işlemi seçiniz")
    @Max(value = 3, message = "3 çeşit ödeme işlemi olabilir")
    @NotNull(message = "pout_paymentType null olamaz!")
    private Integer pout_operationType;

    @ApiModelProperty(value = "Ödeme Türü", dataType = Util.integer, example = "1")
    @Min(value = 1, message = "Ödeme yöntemi seçiniz")
    @Max(value = 3, message = "3 çeşit ödeme yöntemi olabilir")
    @NotNull(message = "pout_paymentType null olamaz!")
    private Integer pout_paymentType;

}
