package com.works.repositories._jpa;


import com.works.entities.Sale;
import com.works.entities.projections.SaleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    @Query(value = "SELECT\n" +
            "\tsale.sale_id AS saleId,\n" +
            "\tsale.created_date AS saledate,\n" +
            "\tsale.sale_code AS salecode,\n" +
            "\tCONCAT( customer.cu_name, \" \", customer.cu_name ) AS customername,\n" +
            "\tCONCAT( product.product_name, \" \", product.product_name ) AS productname,\n" +
            "\tsale.sale_number AS salenumber,\n" +
            "\tsale.sale_type AS saletype,\n" +
            "\tsale.sale_total AS saletotal,\n" +
            "\tsale.sale_detail AS saledetail \n" +
            "FROM\n" +
            "\tsale\n" +
            "\tINNER JOIN customer ON customer.cu_id = sale.cu_id\n" +
            "\tINNER JOIN product ON product.product_id = sale.product_id ORDER BY\n" +
            "\tsale.sale_id DESC;", nativeQuery = true)
    List<SaleInfo> getRows();

    @Query(value = "SELECT\n" +
            "\tsale.sale_id AS saleId,\n" +
            "\tsale.created_date AS saledate,\n" +
            "\tsale.sale_code AS salecode,\n" +
            "\tCONCAT( customer.cu_name, \" \", customer.cu_name ) AS customername,\n" +
            "\tCONCAT( product.product_name, \" \", product.product_name ) AS productname,\n" +
            "\tsale.sale_number AS salenumber,\n" +
            "\tsale.sale_type AS saletype,\n" +
            "\tsale.sale_total AS saletotal,\n" +
            "\tsale.sale_detail AS saledetail \n" +
            "FROM\n" +
            "\tsale\n" +
            "\tINNER JOIN customer ON customer.cu_id = sale.cu_id\n" +
            "\tINNER JOIN product ON product.product_id = sale.product_id \n" +
            "WHERE\n" +
            "\tsale.sale_id LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR sale.created_date LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR sale.sale_code LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR CONCAT( customer.cu_name, \" \", customer.cu_name ) LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR CONCAT( product.product_name, \" \", product.product_name ) LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR sale.sale_number LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR sale.sale_type LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR sale.sale_total LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR sale.sale_detail LIKE CONCAT( '%', ?1, '%' ) ORDER BY\n" +
            "\tsale.sale_id DESC;;", nativeQuery = true)
    List<SaleInfo> getRowsSearching(String searchingKey);

}



