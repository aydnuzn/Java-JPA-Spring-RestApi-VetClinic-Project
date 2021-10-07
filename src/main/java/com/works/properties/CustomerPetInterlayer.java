package com.works.properties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;


@Data
@ApiModel(value = "Müşteri - Pet Ekleme Ara Model", description = "Müşteri ve Pet Aynı Anda Eklenirken Kullanılır.")
public class CustomerPetInterlayer {
    @Valid
    @ApiModelProperty(value = "Müşteri Nesnesi", required = true)
    private CustomerInterlayer customerObj;
    @Valid
    @ApiModelProperty(value = "Pet Listesi")
    private List<PetListInterlayer> petList;
}
