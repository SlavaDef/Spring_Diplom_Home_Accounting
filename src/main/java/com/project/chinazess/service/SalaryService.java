package com.project.chinazess.service;

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
}
