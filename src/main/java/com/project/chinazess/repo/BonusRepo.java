package com.project.chinazess.repo;

import com.project.chinazess.models.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BonusRepo extends JpaRepository<Bonus, Long> {


    List<Bonus> findAllByDate(LocalDate date);

    List<Bonus> findAllByDateBetween(LocalDate date, LocalDate date2);

    @Query(value = "SELECT * FROM bonus " +
            "WHERE DATE >= DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "AND DATE < DATE_FORMAT(NOW(), '%Y-%m-01') + INTERVAL 1 MONTH", nativeQuery = true)
    List<Bonus> findAllDatesByMonth();

    @Query(value = "SELECT * FROM bonus WHERE YEAR(`date`) = YEAR(NOW()) AND WEEK(`date`, 1) = WEEK(NOW(), 1)", nativeQuery = true)
    List<Bonus> findAllDatesByWeek();


}
