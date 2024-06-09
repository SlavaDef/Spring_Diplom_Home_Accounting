package com.project.chinazess.repo;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Presents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PresentsRepo extends JpaRepository<Presents, Long> {

    List<Presents> findAllByDate(LocalDate date);

    Page<Presents> findAllByDate(LocalDate date, Pageable pageable);

    List<Presents> findAllByDateBetween(LocalDate date, LocalDate date2);

    @Query(value = "SELECT * FROM count.presents " +
            "WHERE DATE >= DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "AND DATE < DATE_FORMAT(NOW(), '%Y-%m-01') + INTERVAL 1 MONTH",nativeQuery = true )
    List<Presents> findAllDatesByMonth();

    @Query(value = "SELECT * FROM count.presents WHERE YEAR(`date`) = YEAR(NOW()) AND WEEK(`date`, 1) = WEEK(NOW(), 1)", nativeQuery = true)
    List<Presents> findAllDatesByWeek();

    @Query(value = "SELECT * FROM count.presents WHERE YEAR(`date`) = YEAR(NOW()) AND WEEK(`date`, 1) = WEEK(NOW(), 1)", nativeQuery = true)
    Page<Presents> findAllDatesByWeek(Pageable pageable);

}
