package com.works.repositories._jpa;

import com.works.entities.UserFollowIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserFollowInRepository extends JpaRepository<UserFollowIn, Integer> {

    @Query(value = "SELECT\n" +
            "\tu.user_follow_in_id, u.created_by, u.created_date, u.last_modified_by, u.last_modified_date\n" +
            "FROM\n" +
            "\tuser_follow_in as u\n" +
            "WHERE\n" +
            "\t(created_date BETWEEN NOW() - INTERVAL 30 MINUTE\n" +
            "\tAND NOW()) AND created_by=?1", nativeQuery = true)
    Optional<UserFollowIn> findLastLoginProcesses(String email);

    @Query(value = "Select Count(*) as number From user_follow_in Where DATE(user_follow_in.created_date) >= DATE(NOW()) - INTERVAL 7 DAY;", nativeQuery = true)
    Optional<String> getNumber();

    @Query(value = "SELECT\n" +
            "\tCONCAT( cu_name, \" \", cu_surname ) AS cuname,\n" +
            "\tSUM( sale_total ) \n" +
            "FROM\n" +
            "\tsale\n" +
            "\tINNER JOIN customer ON customer.cu_id = sale.cu_id \n" +
            "WHERE\n" +
            "\tDATE( sale.created_date ) >= DATE(\n" +
            "\tNOW()) - INTERVAL 7 DAY \n" +
            "GROUP BY\n" +
            "\tcustomer.cu_id LIMIT 0,5", nativeQuery = true)
    List<Object[]> ls();

    @Query(value = "SELECT\n" +
            "\tsale.sale_type,\n" +
            "\tSUM( sale.sale_total ) AS totalForType \n" +
            "FROM\n" +
            "\tsale \n" +
            "GROUP BY\n" +
            "\tsale.sale_type \n" +
            "ORDER BY\n" +
            "\ttotalForType DESC", nativeQuery = true)
    List<Object[]> ls2();
}
