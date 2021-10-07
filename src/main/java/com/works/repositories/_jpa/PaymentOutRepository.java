package com.works.repositories._jpa;


import com.works.entities.PaymentOut;
import com.works.entities.projections.AllPayInOutInfo;
import com.works.entities.projections.PayInPayOutInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentOutRepository extends JpaRepository<PaymentOut, Integer> {
    @Query(value = "Select t1.totalPayIn, t2.totalPayOut From\n" +
            "(SELECT SUM(pin_price) as totalPayIn FROM payment_in) as t1,\n" +
            "(Select SUM(pout_price) as totalPayOut From payment_out) as t2" , nativeQuery = true)
    Optional<PayInPayOutInfo> totalPayInOut();

    @Query(value = "SELECT p.pout_id as id, IFNULL(null,'-') as cuName, p.pout_operation_type as processType," +
            " p.pout_payment_type as paymentType, p.pout_price as payPrice, p.pout_detail as payDetail," +
            " p.last_modified_date as lastDate FROM payment_out as p", nativeQuery = true)
    List<AllPayInOutInfo> getAllPayOut(Integer paymentOut);

    @Query(value = "SELECT p.pout_id as id, IFNULL(null,'-') as cuName, p.pout_operation_type as processType,\n" +
            "p.pout_payment_type as paymentType, p.pout_price as payPrice, p.pout_detail as payDetail,\n" +
            "p.last_modified_date as lastDate FROM payment_out as p\n" +
            "ORDER BY lastDate desc LIMIT 0,10", nativeQuery = true)
    List<AllPayInOutInfo> getPayOutLastTenInfo();

    @Query(value="SELECT p.pout_id as id, IFNULL(null,'-') as cuName, p.pout_operation_type as processType, p.pout_payment_type as paymentType, p.pout_price as payPrice, p.pout_detail as payDetail, p.last_modified_date as lastDate FROM payment_out as p\n" +
            "Where p.pout_detail LIKE CONCAT('%',?1,'%');", nativeQuery = true)
    List<AllPayInOutInfo> getAllPayOutSearchList(String strSearch);

}
