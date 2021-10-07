package com.works.properties;

import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Alış Ara Katman Modeli", description = "Alış Ekleme ve Listeleme İçin Kullanılır.")
public class PurchaseInterlayer {

    @ApiModelProperty(value = "Tedarikçi IDsi", required = true, notes = "Tedarikçi id si girilmezse işlem iptal edilir.", dataType = Util.integer, example = "4")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "supplier_id not null!")
    private Integer supplier_id;

    @ApiModelProperty(value = "Ürün_id", required = true, notes = "Ürün_id girilmezse işlem iptal edilir.", dataType = Util.integer, example = "1")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "product_id not null!")
    private Integer product_id;

    @ApiModelProperty(value = "Alış Adeti", required = true, notes = "Alış adeti girilmezse işlem iptal edilir.", example = "1")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "purchase_number not null!")
    private Integer purchase_number;

    @ApiModelProperty(value = "Alış Notu", required = true, notes = "Note girilmezse işlem iptal edilir.", example = "Not")
    @NotEmpty(message = "pNote not empty!")
    @NotNull(message = "pNote not null!")
    private String pNote;

    @ApiModelProperty(value = "Alış Tipi", required = true, notes = "Alış tipi girilmezse işlem iptal edilir.", dataType = Util.integer, example = "1")
    @Min(value = 1, message = "En az 1 olabilir")
    @NotNull(message = "purchase_type not null!")
    private Integer purchase_type;
}