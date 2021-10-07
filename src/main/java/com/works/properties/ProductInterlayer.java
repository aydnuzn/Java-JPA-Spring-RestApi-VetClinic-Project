package com.works.properties;

import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Ürün Ara Katman Modeli", description = "Ürün ekleme,güncelleme için kullanılır")
public class ProductInterlayer {

    @ApiModelProperty(hidden = true)
    private Integer product_id;

    @ApiModelProperty(value = "İsmi", dataType = Util.string, required = true, example = "Çiftlik Tırnak Makası")
    @NotNull(message = "Ürün Adı Null Olamaz!")
    @NotEmpty(message = "Ürün Adı Boş Bırakılamaz!")
    private String product_name;

    @ApiModelProperty(value = "Birimi", dataType = Util.integer, required = true, example = "2")
    @Min(value = 1, message = "En Az 1 Olabilir!")
    @Max(value = 4, message = "En fazla 4 olabilir!")
    @NotNull(message = "product_unit not null!")
    private Integer product_unit;

    @ApiModelProperty(value = "Kategori Türü", dataType = Util.integer, required = true, example = "1")
    @Min(value = 1, message = "En Az 1 Olabilir!")
    @NotNull(message = "category not null!")
    private Integer category;

    @ApiModelProperty(value = "Kdv Orani", dataType = Util.integer, required = true, example = "2")
    @Min(value = 1, message = "En Az 1 Olabilir!")
    @Max(value = 3, message = "En fazla 3 olabilir!")
    @NotNull(message = "product_kdv not null!")
    private Integer product_kdv;

    @ApiModelProperty(value = "Alis Fiyati", dataType = Util.integer, required = true, example = "100")
    @Min(value = 1, message = "En Az 1 Olabilir!")
    @NotNull(message = "product_alis not null!")
    private Integer product_alis;

    @ApiModelProperty(value = "Satis Fiyatı", dataType = Util.integer, required = true, example = "195")
    @NotNull(message = "product_satis not null!")
    @Min(value = 1, message = "En Az 1 Olabilir!")
    private Integer product_satis;

    @ApiModelProperty(value = "Stok Miktarı", dataType = Util.integer, required = true, example = "140")
    @NotNull(message = "product_stokMiktari not null!")
    @Min(value = 1, message = "En Az 1 Olabilir!")
    private Integer product_stokMiktari;

    @ApiModelProperty(value = "Ürün Durumu", dataType = Util.myBoolean, required = true, example = "true")
    @NotNull(message = "statu not null!")
    private Boolean product_statu;
}
