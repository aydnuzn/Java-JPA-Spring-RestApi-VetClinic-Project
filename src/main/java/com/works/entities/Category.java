package com.works.entities;

import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(value = "Kategori Ekleme Modeli", description = "Kategori Ekleme ve Güncelleme İçin Kullanılır.")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Kategori Id", notes = "Kategori güncellerken girilmesi gereklidir.", required = true, dataType = Util.integer, example = "1")
    private Integer category_id;

    @Column(length = 20, unique = true)
    @NotEmpty(message = "Grup Adı boş olamaz!")
    @NotNull(message = "Grup Adı null olamaz!")
    @ApiModelProperty(value = "Kategori Başlığı", notes = "Kategori idsi girilmesi gereklidir.", required = true, dataType = Util.string, example = "İzmirliler")
    private String category_title;

    @Column(length = 40)
    @NotEmpty(message = "Grup Adı boş olamaz!")
    @NotNull(message = "Grup Adı null olamaz!")
    @ApiModelProperty(value = "Kategori Detayı", notes = "Kategori detayı girilmesi gereklidir.", required = true, dataType = Util.string, example = "Note")
    private String category_detail;

}
