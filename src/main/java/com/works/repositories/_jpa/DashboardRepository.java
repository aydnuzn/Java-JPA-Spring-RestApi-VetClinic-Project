package com.works.repositories._jpa;

import com.works.entities.Diary;
import com.works.entities.projections.DashboardInfo;
import com.works.entities.projections.DiaryToDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DashboardRepository extends JpaRepository<Diary, Integer> {
    @Query(value = "SELECT t1.diaryToday, t2.diaryYesterday, t3.registerCustomerToday, t4.registerCustomerYesterday, (IFNULL(t5.todayPayIn,0)-IFNULL(t6.todayPayOut,0)) as todayCiro, (IFNULL(t7.yesterdayPayIn,0)-IFNULL(t8.yesterdayPayOut,0)) as yesterdayCiro, t9.stockValue From\n" +
            "(SELECT COUNT(*) as diaryToday FROM diary INNER JOIN `user` ON diary.us_id = `user`.us_id WHERE DATE(diary.diary_date) = DATE(NOW())) as t1,\n" +
            "(SELECT COUNT(*) as diaryYesterday FROM diary INNER JOIN `user` ON diary.us_id = `user`.us_id WHERE DATE(diary.diary_date) = DATE(NOW()) - INTERVAL 1 DAY) as t2,\n" +
            "(SELECT COUNT(*) as registerCustomerToday From customer WHERE DATE(created_date) = DATE(NOW())) as t3,\n" +
            "(SELECT COUNT(*) as registerCustomerYesterday From customer as cs Where DATE(created_date) = DATE(NOW()) - INTERVAL 1 DAY) as t4,\n" +
            "(SELECT IFNULL(SUM(pin_price),0) as todayPayIn FROM payment_in Where DATE(created_date) = DATE(NOW())) as t5,\n" +
            "(SELECT IFNULL(SUM(pout_price),0) as todayPayOut From payment_out Where DATE(created_date) = DATE(NOW())) as t6,\n" +
            "(SELECT IFNULL(SUM(pin_price),0) as yesterdayPayIn FROM payment_in Where DATE(created_date) = DATE(NOW()) - INTERVAL 1 DAY) as t7,\n" +
            "(SELECT IFNULL(SUM(pout_price),0) as yesterdayPayOut From payment_out Where DATE(created_date) = DATE(NOW()) - INTERVAL 1 DAY) as t8,\n" +
            "(Select IFNULL(Sum((product_alis*product_stok_miktari)),0) as stockValue From product ) as t9", nativeQuery = true)
    Optional<DashboardInfo> getViewDashboard();

    @Query(value ="SELECT t1.`Pazartesi`, t2.`Sali`, t3.`Çarşamba`, t4.`Perşembe`, t5.`Cuma`, t6.`Cumartesi`, t7.`Pazar` From\n" +
            "(SELECT Count(*) as `Pazartesi` From customer Where DATE(customer.created_date) = DATE(NOW() + INTERVAL ( - WEEKDAY(NOW())) DAY)) as t1,\n" +
            "(SELECT Count(*) as `Sali` From customer Where DATE(customer.created_date) = DATE(NOW() + INTERVAL ( 1 - WEEKDAY(NOW())) DAY)) as t2,\n" +
            "(SELECT Count(*) as `Çarşamba` From customer Where DATE(customer.created_date) = DATE(NOW() + INTERVAL (2 - WEEKDAY(NOW())) DAY)) as t3,\n" +
            "(SELECT Count(*) as `Perşembe` From customer Where DATE(customer.created_date) = DATE(NOW() + INTERVAL (3 - WEEKDAY(NOW())) DAY)) as t4,\n" +
            "(SELECT Count(*) as `Cuma` From customer Where DATE(customer.created_date) = DATE(NOW() + INTERVAL (4 - WEEKDAY(NOW())) DAY)) as t5,\n" +
            "(SELECT Count(*) as `Cumartesi` From customer Where DATE(customer.created_date) = DATE(NOW() + INTERVAL (5 - WEEKDAY(NOW())) DAY)) as t6,\n" +
            "(SELECT Count(*) as `Pazar` From customer Where DATE(customer.created_date) = DATE(NOW() + INTERVAL (6 - WEEKDAY(NOW())) DAY)) as t7", nativeQuery = true)
    Object[] registerCustomerDaysOfWeek();

    @Query(value = "Select tPet.ty_name, Count(*) as petNumber \n" +
            "From join_type_breed_pet as tBreed\n" +
            "INNER JOIN type_pet as tPet ON tBreed.ty_id = tPet.ty_id\n" +
            "Group By tBreed.ty_id", nativeQuery = true)
    List<Object[]> petTypeCount();


    @Query(value = "SELECT c.cu_name as cuName, c.cu_surname as cuSurname, CONCAT(d.diary_date,' ',d.diary_time) as diaryDate FROM diary as d\n" +
            "INNER JOIN customer as c ON d.cu_id = c.cu_id\n" +
            "Where DATE(NOW()) = diary_date AND TIME(NOW()) > diary_time", nativeQuery = true)
    List<DiaryToDashboard> getExpiredDiary();

    @Query(value = "SELECT c.cu_name as cuName, c.cu_surname as cuSurname, CONCAT(d.diary_date,' ',d.diary_time) as diaryDate FROM diary as d\n" +
            "INNER JOIN customer as c ON d.cu_id = c.cu_id\n" +
            "Where DATE(NOW()) = diary_date AND TIME(NOW()) <= diary_time", nativeQuery = true)
    List<DiaryToDashboard> getUpcomingDiary();


}