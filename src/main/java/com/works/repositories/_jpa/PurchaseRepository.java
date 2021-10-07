package com.works.repositories._jpa;

import com.works.entities.Purchase;
import com.works.entities.projections.PurchaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    @Query(value = "SELECT\n" +
            "\tpurchase.created_date AS date,\n" +
            "\tpurchase.purchase_id,\n" +
            "\tpurchase.purchase_type,\n" +
            "\tpurchase.created_date as purchase_date,\n" +
            "\tpurchase.purchase_code,\n" +
            "\tsupplier.supplier_name AS supplier,\n" +
            "\tproduct.product_name AS product,\n" +
            "\tpurchase.purchase_number AS purchase_number,\n" +
            "\tproduct.product_alis AS purchase_total,\n" +
            "\tpurchase.purchase_detail \n" +
            "FROM\n" +
            "\tsupplier\n" +
            "\tJOIN purchase ON supplier.supplier_id = purchase.supplier_id\n" +
            "\tJOIN product ON product.product_id = purchase.product_id", nativeQuery = true)
    List<PurchaseInfo> getRows();

    @Query(value = "SELECT\n" +
            "\tpurchase.created_date AS date,\n" +
            "\tpurchase.purchase_id,\n" +
            "\tpurchase.purchase_type,\n" +
            "\tpurchase.created_date as purchase_date,\n" +
            "\tpurchase.purchase_code,\n" +
            "\tsupplier.supplier_name AS supplier,\n" +
            "\tproduct.product_name AS product,\n" +
            "\tpurchase.purchase_number AS purchase_number,\n" +
            "\tproduct.product_alis AS purchase_total,\n" +
            "\tpurchase.purchase_detail \n" +
            "FROM\n" +
            "\tsupplier\n" +
            "\tJOIN purchase ON supplier.supplier_id = purchase.supplier_id\n" +
            "\tJOIN product ON product.product_id = purchase.product_id \n" +
            "\twhere supplier.supplier_name  LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR product.product_name LIKE CONCAT( '%', ?1, '%' );", nativeQuery = true)
    List<PurchaseInfo> getRowsSearching(String search);

    @Query(value = "SELECT purchase_id FROM purchase WHERE supplier_id =?1",nativeQuery = true)
    List<Integer> getAllPurchaseId(Integer i);

}