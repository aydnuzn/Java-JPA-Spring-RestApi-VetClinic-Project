package com.works.entities;

import com.works.entities.listener.BaseEntity;
import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Müşteri Grubu Ekleme Modeli", description = "Müşteri Grubu Ekleme ve Güncelleme İçin Kullanılır.")
public class CustomerGroup extends BaseEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Müşteri grubu idsi", dataType = Util.integer, example = "1")
    private Integer cu_gr_id;

    @Column(length = 30, unique = true)
    @NotEmpty(message = "Grup Adı boş olamaz!")
    @NotNull(message = "Grup Adı null olamaz!")
    @Length(min = 3, max = 30, message = "Grup adı an az 3, en fazla 30 karakter olabilir")
    @ApiModelProperty(value = "Müşteri grubu adı", required = true, dataType = Util.string, example = "Çiftlik Malzemeleri")
    private String cu_gr_name;
}
