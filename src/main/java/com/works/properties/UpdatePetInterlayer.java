package com.works.properties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Sadece Pet Güncelleme Ara Katman Modeli", description = "Pet Güncelleme İçin Kullanılır.")
public class UpdatePetInterlayer extends PetListInterlayer {
    @ApiModelProperty(value = "Müşteri-Pet ara tablo sıra numarası", required = true, notes = "Müşter-Pet ara tablo  sıra numarası girilmezse işlemler iptal edilir.", example = "1")
    @NotNull(message = "jtbpId NULL OLAMAZ")
    @Min(value = 0, message = "En az 0 olabilir.")
    private Integer jtbpId;
    @ApiModelProperty(value = "Pet sıra numarası", required = true, notes = "Pet sıra numarası girilmezse işlemler iptal edilir.", example = "1")
    @NotNull(message = "petId NULL OLAMAZ")
    @Min(value = 0, message = "En az 0 olabilir.")
    private Integer petId;
}
