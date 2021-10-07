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

@Data
@Entity
@ApiModel(value = "Tedarikçi Ekleme Modeli", description = "Tedarikçi Ekleme ve Güncelleme İşlemi")
public class Supplier extends BaseEntity<String> { //Tedarikçi

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Tedarikçi Id", notes = "Tedarikçi güncellerken girilmesi gereklidir.", dataType = Util.integer, example = "1")
    private Integer supplier_id;

    @NotNull(message = "İsim null olamaz")
    @NotEmpty(message = "İsim boş olamaz")
    @Length(min = 2, max = 30, message = "İsim alanı en az 2, en fazla 30 karakter olabilir")
    @ApiModelProperty(value = "Tedarikçi Adı", notes = "Tedarikçi adı girilmesi gereklidir.", required = true, dataType = Util.string, example = "Bilmem AŞ")
    private String supplier_name;

    @NotNull(message = "Mail Null olamaz")
    @NotEmpty(message = "Mail Boş olamaz")
    @Column(unique = true)
    @ApiModelProperty(value = "Tedarikçi Maili", notes = "Tedarikçi maili girilmesi gereklidir.", required = true, dataType = Util.string, example = "bilmem@mail.com")
    private String supplier_mail;

    @NotNull(message = "Tel Null olamaz")
    @NotEmpty(message = "Tel Boş olamaz")
    @ApiModelProperty(value = "Tedarikçi Tel", notes = "Tedarikçi teli girilmesi gereklidir.", required = true, dataType = Util.string, example = "Cake")
    private String supplier_tel;

    @Column(length = 10)
    @ApiModelProperty(value = "Tedarikçi Durumu", notes = "Tedarikçi durumu girilmesi gereklidir.", required = true, dataType = Util.string, example = "true")
    private String supplier_statu;
}
