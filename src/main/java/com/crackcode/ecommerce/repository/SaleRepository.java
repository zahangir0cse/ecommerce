package com.crackcode.ecommerce.repository;

import com.crackcode.ecommerce.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT SUM(s.totalAmount) FROM Sale s WHERE s.date = :date")
    Double getTotalSaleAmountByDate(LocalDate date);

    @Query("SELECT s.date, SUM(s.totalAmount) as total FROM Sale s WHERE s.date BETWEEN :startDate AND :endDate GROUP BY s.date ORDER BY total DESC")
    List<Object[]> getMaxSaleDayInRange(LocalDate startDate, LocalDate endDate);

    @Query("SELECT s.item.name, SUM(s.totalAmount) as total FROM Sale s GROUP BY s.item.name ORDER BY total DESC LIMIT 5")
    List<Object[]> getTopSellingItemsAllTime();

    @Query("SELECT s.item.name, COUNT(s.item.id) as count FROM Sale s WHERE s.date >= :startDate GROUP BY s.item.name ORDER BY count DESC LIMIT 5")
    List<Object[]> getTopSellingItemsLastMonth(LocalDate startDate);

}
