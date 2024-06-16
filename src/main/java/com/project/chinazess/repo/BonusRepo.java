package com.project.chinazess.repo;

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
public interface BonusRepo extends JpaRepository<Bonus, Long> {


    List<Bonus> findAllByDate(LocalDate date);

    Page<Bonus> findAllByDate(LocalDate date, Pageable pageable);

    List<Bonus> findAllByDateBetween(LocalDate date, LocalDate date2);

    @Query(value = "SELECT * FROM count.bonus WHERE YEAR(`date`) = YEAR(NOW()) AND WEEK(`date`, 1) = WEEK(NOW(), 1)", nativeQuery = true)
    List<Bonus> findAllDatesByWeek();

    @Query(value = "SELECT * FROM count.bonus WHERE YEAR(`date`) = YEAR(NOW()) AND WEEK(`date`, 1) = WEEK(NOW(), 1)", nativeQuery = true)
    Page<Bonus> findAllDatesByWeek(Pageable pageable);

    @Query(value = "SELECT * FROM count.bonus " +
            "WHERE DATE >= DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "AND DATE < DATE_FORMAT(NOW(), '%Y-%m-01') + INTERVAL 1 MONTH", nativeQuery = true)
    List<Bonus> findAllDatesByMonth();

    @Query(value = "SELECT * FROM count.bonus " +
            "WHERE DATE >= DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "AND DATE < DATE_FORMAT(NOW(), '%Y-%m-01') + INTERVAL 1 MONTH", nativeQuery = true)
    Page<Bonus> findAllDatesByMonth(Pageable pageable);





}
