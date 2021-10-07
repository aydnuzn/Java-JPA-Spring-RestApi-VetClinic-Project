package com.works.restcontroller;

import com.works.dto.rest.PaymentRestDto;
import com.works.entities.PaymentOut;
import com.works.properties.PayInInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/payment")
@Api(value = "Payment Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class PaymentRestController {

    final PaymentRestDto paymentRestDto;

    public PaymentRestController(PaymentRestDto paymentRestDto) {
        this.paymentRestDto = paymentRestDto;
    }

    @ApiOperation(value = "Tüm Kasa Girişlerini Listeleme Servisi")
    @GetMapping("/getAllPayIn")
    public Map<REnum, Object> getAllPayIn() {
        return paymentRestDto.getAllPayIn();
    }

    @ApiOperation(value = "Son 10 Kasa Girişini Listeleme Servisi")
    @GetMapping("/getPayInLastTen")
    public Map<REnum, Object> getPayInLastTen() {
        return paymentRestDto.getPayInLastTen();
    }

    @ApiOperation(value = "Tüm Kasa Çıkışlarını Listeleme Servisi")
    @GetMapping("/getAllPayOut")
    public Map<REnum, Object> getAllPayOut() {
        return paymentRestDto.getAllPayOut();
    }

    @ApiOperation(value = "Son 10 Kasa Çıkışını Listeleme Servisi")
    @GetMapping("/getPayOutLastTen")
    public Map<REnum, Object> getPayOutLastTen() {
        return paymentRestDto.getPayOutLastTen();
    }

    @ApiOperation(value = "Tüm Kasa Girişlerini ve Çıkışlarını Listeleme Servisi")
    @GetMapping("/getAllPayInOut")
    public Map<REnum, Object> getAllPayInOut() {
        return paymentRestDto.getAllPayInOut();
    }

    @ApiOperation(value = "Kasaya Son 10 Giriş-Çıkış Servisi")
    @GetMapping("/getPaymentLastTenInfo")
    public Map<REnum, Object> getPaymentLastTenInfo() {
        return paymentRestDto.getPaymentLastTenInfo();
    }

    @ApiOperation(value = "Borcu Bulunan Müşteri Listeleme Servisi")
    @GetMapping("/getDebtorCustomer")
    public Map<REnum, Object> debtorCustomer() {
        return paymentRestDto.debtorCustomer();
    }

    @ApiOperation(value = "Kasaya Giren ve Çıkan Para Toplam Servisi")
    @GetMapping("/getPaymentTotalInOutInfo")
    public Map<REnum, Object> getPaymentTotalInOutInfo() {
        return paymentRestDto.getPaymentTotalInOutInfo();
    }

    @ApiOperation(value = "Kasaya Para Giriş Servisi")
    @PostMapping("/addPayIn")
    public Map<REnum, Object> insertPayIn(@RequestBody @Valid PayInInterlayer payInInterlayer, BindingResult bindingResult) {
        return paymentRestDto.insertPayIn(payInInterlayer, bindingResult);
    }

    @ApiOperation(value = "Kasadan Para Çıkış Servisi")
    @PostMapping("/addPayOut")
    public Map<REnum, Object> insertPayOut(@RequestBody @Valid PaymentOut paymentOut, BindingResult bindingResult) {
        return paymentRestDto.insertPayOut(paymentOut, bindingResult);
    }

    @ApiOperation(value = "Kasa Para Girişi Silme Servisi")
    @DeleteMapping("/deletePayIn/{stIndex}")
    public Map<REnum, Object> deletePayIn(@PathVariable String stIndex) {
        return paymentRestDto.deletePayIn(stIndex);
    }

    @ApiOperation(value = "Kasa Para Öıkışı Silme Servisi")
    @DeleteMapping("/deletePayOut/{stIndex}")
    public Map<REnum, Object> deletePayOut(@PathVariable String stIndex) {
        return paymentRestDto.deletePayOut(stIndex);
    }

    @ApiOperation(value = "Kasaya Para Girişi Arama Servisi")
    @GetMapping("/searchPayIn/{strSearch}")
    public Map<REnum, Object> getPayInSearchList(@PathVariable String strSearch) {
        return paymentRestDto.getPayInSearchList(strSearch);
    }

    @ApiOperation(value = "Kasaya Para Çıkışı Servisi")
    @GetMapping("/searchPayOut/{strSearch}")
    public Map<REnum, Object> getPayOutSearchList(@PathVariable String strSearch) {
        return paymentRestDto.getPayOutSearchList(strSearch);
    }

    @ApiOperation(value = "Kasaya Para Giriş-Çıkış Arama Servisi")
    @GetMapping("/searchPayInOut/{strSearch}")
    public Map<REnum, Object> getPayInOutSearchList(@PathVariable String strSearch) {
        return paymentRestDto.getPayInOutSearchList(strSearch);
    }

}