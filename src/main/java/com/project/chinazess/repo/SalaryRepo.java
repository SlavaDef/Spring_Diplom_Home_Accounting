package com.project.chinazess.repo;

import com.project.chinazess.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, Long> {

    List<Salary> findAllByDate(LocalDate date);

    List<Salary> findAllByDateBetween(LocalDate date, LocalDate date2);

    @Query(value = "SELECT * FROM count.salary " +
            "WHERE DATE >= DATE_FORMAT(NOW(), '%Y-%m-01')\n" +
            "AND DATE < DATE_FORMAT(NOW(), '%Y-%m-01') + INTERVAL 1 MONTH", nativeQuery = true)
    List<Salary> findAllDatesByMonth();

    @Query(value = "SELECT * FROM salary WHERE YEAR(`date`) = YEAR(NOW()) AND WEEK(`date`, 1) = WEEK(NOW(), 1)", nativeQuery = true)
    List<Salary> findAllDatesByWeek();

}
