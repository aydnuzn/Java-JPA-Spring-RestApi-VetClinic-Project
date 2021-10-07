package com.works.repositories._jpa;


import com.works.entities.PaymentIn;
import com.works.entities.projections.AllPayInOutInfo;
import com.works.entities.projections.DebtorCustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentInRepository extends JpaRepository<PaymentIn, Integer> {
    @Query(value = "Select t1.cu_id as cuId, t1.cu_name as cuName, IFNULL(t1.totalPrice,0) as totalPrice, IFNULL(t2.paymentTotal,0) as totalPayment FROM \n" +
            "(Select cs.cu_id, cs.cu_name, Sum(s.sale_total) as totalPrice from customer as cs\n" +
            "INNER JOIN sale as s ON cs.cu_id = s.cu_id GROUP BY cs.cu_id) as t1\n" +
            "LEFT JOIN \n" +
            "(Select cs.cu_id, SUM(p.pin_price) as paymentTotal From customer as cs\n" +
            "Inner Join payment_in as p ON cs.cu_id = p.cu_id Group By cs.cu_id) \n" +
            "as t2 ON t1.cu_id = t2.cu_id \n" +
            "UNION\n" +
            "Select t1.cu_id, t1.cu_name, t1.totalPrice, t2.paymentTotal FROM \n" +
            "(Select cs.cu_id, cs.cu_name, Sum(s.sale_total) as totalPrice from customer as cs\n" +
            "INNER JOIN sale as s ON cs.cu_id = s.cu_id GROUP BY cs.cu_id) as t1\n" +
            "RIGHT JOIN (Select cs.cu_id, SUM(p.pin_price) as paymentTotal From customer as cs\n" +
            "Inner Join payment_in as p ON cs.cu_id = p.cu_id\n" +
            "Group By cs.cu_id) as t2 ON t1.cu_id = t2.cu_id", nativeQuery = true)
    List<DebtorCustomerInfo> debtorCustomerInfo();

    @Query(value = "SELECT p.pout_id as id,IFNULL(null, '-') as cuName, p.pout_operation_type as processType, p.pout_payment_type as paymentType, p.pout_price as payPrice, p.pout_detail as payDetail, p.last_modified_date as lastDate FROM payment_out as p\n" +
            "UNION SELECT p.pin_id, cs.cu_name, p.pin_operation_type, p.pin_payment_type, p.pin_price, p.pin_detail, p.last_modified_date FROM payment_in as p\n" +
            "INNER JOIN customer as cs ON p.cu_id = cs.cu_id\n" +
            "ORDER BY lastDate desc", nativeQuery = true)
    List<AllPayInOutInfo> getAllPayInfo();

    @Query(value = "SELECT p.pout_id as id,IFNULL(null, '-') as cuName, p.pout_operation_type as processType, p.pout_payment_type as paymentType, p.pout_price as payPrice, p.pout_detail as payDetail, p.last_modified_date as lastDate FROM payment_out as p\n" +
            "UNION SELECT p.pin_id, cs.cu_name, p.pin_operation_type, p.pin_payment_type, p.pin_price, p.pin_detail, p.last_modified_date FROM payment_in as p\n" +
            "INNER JOIN customer as cs ON p.cu_id = cs.cu_id\n" +
            "ORDER BY lastDate desc LIMIT 0,10", nativeQuery = true)
    List<AllPayInOutInfo> getPayLastTenInfo();

    @Query(value = "SELECT p.pin_id as id, c.cu_name as cuName, p.pin_operation_type as processType, p.pin_payment_type as paymentType, p.pin_price as payPrice, p.pin_detail as payDetail, p.last_modified_date as lastDate FROM payment_in as p\n" +
            "Inner Join customer as c ON p.cu_id = c.cu_id", nativeQuery = true)
    List<AllPayInOutInfo> getAllPayIn(Integer paymentIn);

    @Query(value = "SELECT p.pin_id as id, c.cu_name as cuName, p.pin_operation_type as processType, p.pin_payment_type as paymentType, p.pin_price as payPrice, p.pin_detail as payDetail, p.last_modified_date as lastDate FROM payment_in as p\n" +
            "Inner Join customer as c ON p.cu_id = c.cu_id\n" +
            "ORDER BY lastDate desc LIMIT 0,10", nativeQuery = true)
    List<AllPayInOutInfo> getAllPayInLastTen();

    @Query(value = "SELECT p.pout_id as id,IFNULL(null, '') as cuName, p.pout_operation_type as processType, p.pout_payment_type as paymentType, p.pout_price as payPrice, p.pout_detail as payDetail, p.last_modified_date as lastDate FROM payment_out as p\n" +
            "WHERE \n" +
            "IFNULL(null, '-') LIKE CONCAT('%', ?1,'%') OR\n" +
            "p.pout_detail LIKE CONCAT('%', ?1,'%')\n" +
            "UNION SELECT p.pin_id, cs.cu_name, p.pin_operation_type, p.pin_payment_type, p.pin_price, p.pin_detail, p.last_modified_date FROM payment_in as p\n" +
            "INNER JOIN customer as cs ON p.cu_id = cs.cu_id\n" +
            "WHERE \n" +
            "cs.cu_name LIKE CONCAT('%', ?1,'%') OR\n" +
            "p.pin_detail LIKE CONCAT('%', ?1,'%')\n" +
            "ORDER BY lastDate desc", nativeQuery = true)
    List<AllPayInOutInfo> getPaySearchList(String strSearch);

    @Query(value="SELECT p.pin_id as id, c.cu_name as cuName, p.pin_operation_type as processType, p.pin_payment_type as paymentType, p.pin_price as payPrice, p.pin_detail as payDetail, p.last_modified_date as lastDate FROM payment_in as p\n" +
            "Inner Join customer as c ON p.cu_id = c.cu_id\n" +
            "Where c.cu_name LIKE CONCAT('%',?1,'%') OR\n" +
            "p.pin_detail LIKE CONCAT('%',?1,'%')", nativeQuery = true)
    List<AllPayInOutInfo> getAllPayInSearchList(String strSearch);

}
