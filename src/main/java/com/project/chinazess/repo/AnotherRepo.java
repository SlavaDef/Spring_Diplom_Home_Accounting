package com.project.chinazess.repo;

import com.project.chinazess.models.Another;
import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnotherRepo extends JpaRepository<Another, Long> {

    List<Another> findAllByDate(LocalDate date);

    Page<Another> findAllByDate(LocalDate date, Pageable pageable);

    List<Another> findAllByDateBetween(LocalDate date, LocalDate date2);

    @Query(value = "SELECT * FROM count.another " +
            "WHERE DATE >= DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "AND DATE < DATE_FORMAT(NOW(), '%Y-%m-01') + INTERVAL 1 MONTH", nativeQuery = true)
    List<Another> findAllDatesByMonth();

    @Query(value = "SELECT * FROM count.another " +
            "WHERE DATE >= DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "AND DATE < DATE_FORMAT(NOW(), '%Y-%m-01') + INTERVAL 1 MONTH", nativeQuery = true)
    Page<Another> findAllDatesByMonth(Pageable pageable);

    @Query(value = "SELECT * FROM count.another WHERE YEAR(`date`) = YEAR(NOW()) AND WEEK(`date`, 1) = WEEK(NOW(), 1)", nativeQuery = true)
    List<Another> findAllDatesByWeek();

    @Query(value = "SELECT * FROM count.another WHERE YEAR(`date`) = YEAR(NOW()) AND WEEK(`date`, 1) = WEEK(NOW(), 1)", nativeQuery = true)
    Page<Another> findAllDatesByWeek(Pageable pageable);
}
