package com.works.properties;

import com.works.utils.Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "Müşteri Ara Katman Modeli", description = "Müşteri Ekleme ve Güncelleme İçin Kullanılır.")
public class CustomerInterlayer {

    @ApiModelProperty(value = "Müşteri Adı", required = true, notes = "İsim girilmezse işlemler iptal edilir.", example = "Ali")
    @NotNull(message = "cu_name NULL OLAMAZ")
    @NotEmpty(message = "cu_name EMPTY OLAMAZ")
    private String cu_name;

    @ApiModelProperty(value = "Müşteri Soyadı", required = true, notes = "Soyisim girilmezse işlemler iptal edilir.", example = "Bilmem")
    @NotNull(message = "cu_surname NULL OLAMAZ")
    @NotEmpty(message = "cu_surname EMPTY OLAMAZ")
    private String cu_surname;

    @ApiModelProperty(value = "Müşteri Tel-1", required = true, notes = "Telefon1 girilmezse işlemler iptal edilir.", example = "5148123541")
    @NotNull(message = "cu_tel1 NULL OLAMAZ")
    @NotEmpty(message = "cu_tel1 EMPTY OLAMAZ")
    private String cu_tel1;

    @ApiModelProperty(value = "Müşteri Tel-2", example = "5148123541")
    @NotNull(message = "cu_tel2 NULL OLAMAZ")
    private String cu_tel2;

    @ApiModelProperty(value = "Müşteri Mail", required = true, notes = "Mail girilmezse işlemler iptal edilir.", example = "ali@mail.com")
    @NotNull(message = "cu_mail NULL OLAMAZ")
    @NotEmpty(message = "cu_mail EMPTY OLAMAZ")
    private String cu_mail;

    @ApiModelProperty(value = "Müşteri Vergi Dairesi Adı", example = "Kocaeli Vergi")
    @NotNull(message = "cu_taxname NULL OLAMAZ")
    private String cu_taxname;

    @ApiModelProperty(value = "Müşteri Notu", example = "Ali kişisi ")
    @NotNull(message = "cu_note NULL OLAMAZ")
    private String cu_note;

    @ApiModelProperty(value = "Müşteri Kimlik Numarası", required = true, notes = "Kimlik numarası girilmezse işlemler iptal edilir.", example = "74134801236")
    @NotNull(message = "cu_tcnumber NULL OLAMAZ")
    @NotEmpty(message = "cu_tcnumber EMPTY OLAMAZ")
    private String cu_tcnumber;

    @ApiModelProperty(value = "Müşteri İli Numarası", required = true, dataType = Util.integer, notes = "İl numarası girilmezse işlemler iptal edilir.", example = "1")
    @NotNull(message = "cu_cities NULL OLAMAZ")
    @NotEmpty(message = "cu_cities EMPTY OLAMAZ")
    private String cu_cities;

    @ApiModelProperty(value = "Müşteri İlçesi Numarası", required = true, dataType = Util.integer, notes = "İlçe numarası girilmezse işlemler iptal edilir.", example = "1")
    @NotNull(message = "cu_districts NULL OLAMAZ")
    @NotEmpty(message = "cu_districts EMPTY OLAMAZ")
    private String cu_districts;

    @ApiModelProperty(value = "Müşteri Grubu Numarası", required = true, dataType = Util.integer, notes = "Müşteri grubu numarası girilmezse işlemler iptal edilir.", example = "1")
    @NotNull(message = "cu_group NULL OLAMAZ")
    @NotEmpty(message = "cu_group EMPTY OLAMAZ")
    private String cu_group;

    @ApiModelProperty(value = "Müşteri Adresi", required = true, notes = "Müşteri adresi girilmezse işlemler iptal edilir.", example = "Kocaeli Cumhuriyet Parkı")
    @NotNull(message = "cu_address NULL OLAMAZ")
    @NotEmpty(message = "cu_address EMPTY OLAMAZ")
    private String cu_address;

    @ApiModelProperty(value = "Müşteri İndirim Oranı", required = true, dataType = Util.integer, notes = "Müşteri indirim oranı girilmezse işlemler iptal edilir.", example = "5")
    @NotNull(message = "cu_rateOfDiscount NULL OLAMAZ")
    @NotEmpty(message = "cu_rateOfDiscount EMPTY OLAMAZ")
    private String cu_rateOfDiscount;

    @ApiModelProperty(value = "Müşteri Sms Bildirim Durumu", required = true, dataType = Util.integer, notes = "Sms bildirim durumu girilmezse işlemler iptal edilir.", example = "1")
    @NotNull(message = "cu_smsNotice NULL OLAMAZ")
    @NotEmpty(message = "cu_smsNotice EMPTY OLAMAZ")
    private String cu_smsNotice;

    @ApiModelProperty(value = "Müşteri Mail Bildirim Durumu", required = true, dataType = Util.integer, notes = "Mail bildirim durumu girilmezse işlemler iptal edilir.", example = "1")
    @NotNull(message = "cu_mailNotice NULL OLAMAZ")
    @NotEmpty(message = "cu_mailNotice EMPTY OLAMAZ")
    private String cu_mailNotice;

}
