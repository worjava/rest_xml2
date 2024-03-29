package com.shop.soap_server.repository;

import com.shop.soap_server.model.PurchaseInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfo, Long> {

    @Query("SELECT p FROM PurchaseInfo p JOIN p.user u WHERE p.purchaseDate BETWEEN :startDate AND :endDate")
    List<PurchaseInfo> findPurchasesLastWeek(LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT p.purchase_item, COUNT(p.purchase_item) AS count\n" +
            "FROM purchase_info p\n" +
            "WHERE p.purchase_date BETWEEN :startDate AND :endDate\n" +
            "GROUP BY p.purchase_item\n" +
            "ORDER BY count DESC\n" +
            "LIMIT 1;", nativeQuery = true)
    List<Object[]> findMostPurchasedItemLastMonth(LocalDate startDate, LocalDate endDate);


    @Query(value = "SELECT au.first_name, au.last_name, COUNT(pi.id) AS purchaseCount " +
            "FROM purchase_info pi " +
            "JOIN app_user au ON pi.user_id = au.id " +
            "WHERE pi.purchase_date >= :startDate " +
            "GROUP BY au.first_name, au.last_name " +
            "ORDER BY purchaseCount DESC " +
            "LIMIT 1", nativeQuery = true)
    List<Object[]> findTopBuyerOverLastSixMonths(LocalDate startDate);


    @Query(value = "SELECT pi.purchase_item, COUNT(pi.purchase_item) AS count " +
            "FROM purchase_info pi " +
            "JOIN app_user au ON pi.user_id = au.id " +
            "WHERE au.age = 18 " +
            "GROUP BY pi.purchase_item " +
            "ORDER BY count DESC " +
            "LIMIT 1", nativeQuery = true)
    List<Object[]> getTopItemPurchasedBy18YearOlds();


}






