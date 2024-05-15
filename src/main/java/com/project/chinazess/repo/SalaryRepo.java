package com.project.chinazess.repo;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, Long> {

    List<Salary> findAllByDate(LocalDate date);
}
