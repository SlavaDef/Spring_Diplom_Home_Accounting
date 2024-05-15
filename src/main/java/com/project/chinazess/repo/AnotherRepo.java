package com.project.chinazess.repo;

import com.project.chinazess.models.Another;
import com.project.chinazess.models.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnotherRepo extends JpaRepository<Another, Long> {

    List<Another> findAllByDate(LocalDate date);
    List<Another> findAllByDateBetween(LocalDate date, LocalDate date2);

    @Query(value = "SELECT * FROM another " +
            "WHERE DATE >= DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "AND DATE < DATE_FORMAT(NOW(), '%Y-%m-01') + INTERVAL 1 MONTH",nativeQuery = true )
    List<Another> findAllDatesByMonth();
}
