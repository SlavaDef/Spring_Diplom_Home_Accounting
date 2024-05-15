package com.project.chinazess.service;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Salary;
import com.project.chinazess.repo.SalaryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class SalaryService {

    private SalaryRepo salaryRepo;

    @Transactional
    public Salary addSalary(Salary salary) {
        salary.setDate(LocalDate.now());
        return salaryRepo.save(salary);

    }


    @Transactional
    public Long getSalaryCount() {
        Long count = 0L;
        List<Salary> salaries = salaryRepo.findAll();

        for (Salary salary : salaries) {
            count += salary.getSalary();
        }
        return count;
    }

    @Transactional
    public List<Salary> getAllSalaries() {
        return salaryRepo.findAll();
    }

    @Transactional
    public Long findBonusByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        Long count = 0L;
        List<Salary>  salaries = salaryRepo.findAllByDate(test);
        for (Salary sal : salaries) {
            count += sal.getSalary();
        }
        return count;
    }
}
