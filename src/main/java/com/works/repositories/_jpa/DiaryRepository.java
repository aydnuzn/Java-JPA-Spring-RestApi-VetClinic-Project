package com.works.repositories._jpa;

import com.works.entities.Diary;
import com.works.entities.projections.DiaryInfo;
import com.works.entities.projections.DiaryInfoToIstaticticPageCustomer;
import com.works.entities.projections.DiaryInfoToIstatisticPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    @Query(value = "SELECT\n" +
            "\tdiary_id,\n" +
            "\tdiary_title,\n" +
            "\tdiary_description,\n" +
            "\tdiary_date,\n" +
            "\tdiary_time,\n" +
            "\tdiary_statu,\n" +
            "\tCONCAT( `user`.us_name,\" \", `user`.us_surname ) AS usname,\n" +
            "\tCONCAT( `customer`.cu_name,\" \", `customer`.cu_surname ) AS cuname \n" +
            "FROM\n" +
            "\tDiary\n" +
            "\tINNER JOIN `user` ON diary.us_id = USER.us_id\n" +
            "\tINNER JOIN customer ON customer.cu_id = diary.cu_id", nativeQuery = true)
    List<DiaryInfo> allDiaryList();

    @Query(value = "SELECT\n" +
            "\tdiary_id,\n" +
            "\tdiary_title,\n" +
            "\tdiary_description,\n" +
            "\tdiary_date,\n" +
            "\tdiary_time,\n" +
            "\tdiary_statu,\n" +
            "\tCONCAT( `user`.us_name, \" \", `user`.us_surname ) AS usname,\n" +
            "\tCONCAT( `customer`.cu_name, \" \", `customer`.cu_surname ) AS cuname \n" +
            "FROM\n" +
            "\tDiary\n" +
            "\tINNER JOIN `user` ON diary.us_id = USER.us_id\n" +
            "\tINNER JOIN customer ON customer.cu_id = diary.cu_id \n" +
            "\tAND diary.diary_title LIKE CONCAT( '%', ?1, '%' );", nativeQuery = true)
    List<DiaryInfo> allDiaryListSearch(String search);

    @Query(value = "SELECT DAYOFWEEK(DATE(created_date) - INTERVAL 1 DAY) as daysOfWeek, COUNT(*) as diaryNumber\n" +
            "FROM customer GROUP BY DAYOFWEEK(DATE(created_date)) ORDER BY diaryNumber DESC LIMIT 0,1", nativeQuery = true)
    Optional<DiaryInfoToIstatisticPage> findBusiestDayOfWeek();

    @Query(value = "select CONCAT(customer.cu_name,\" \",customer.cu_surname) as cu_name , COUNT(diary.diary_id) as count_diary from diary\n" +
            "inner JOIN customer on diary.cu_id = customer.cu_id\n" +
            "GROUP BY customer.cu_id ORDER BY count_diary DESC LIMIT 0,1;", nativeQuery = true)
    Optional<DiaryInfoToIstaticticPageCustomer> findBusiestCustomer();

}
