package com.example.zbd.repository;

import com.example.zbd.dto.BookStatsDTO;
import com.example.zbd.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    long countByCustomerId(Integer customerId);

    @Query("SELECT new com.example.zbd.dto.BookStatsDTO(b.title, COUNT(DISTINCT o.customer), SUM(od.quantity), AVG(od.unitPrice)) " +
            "FROM Order o " +
            "JOIN o.orderDetails od " +
            "JOIN od.book b " +
            "WHERE o.date BETWEEN :startDate AND :endDate " +
            "AND EXISTS (SELECT 1 FROM Review r WHERE r.book = b AND r.rating >= 4) " +
            "GROUP BY b.title " +
            "HAVING SUM(od.quantity) > 100 " +
            "ORDER BY SUM(od.quantity)")
    List<BookStatsDTO> findBookStatistics(@Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);

}
