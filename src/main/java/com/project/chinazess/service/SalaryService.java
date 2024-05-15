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
       // Long count = 0L;
       // List<Salary> salaries = salaryRepo.findAll();
        return returnCount(salaryRepo.findAll());
    }

    @Transactional
    public List<Salary> getAllSalaries() {
        return salaryRepo.findAll();
    }

    @Transactional
    public Long findSalaryByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        List<Salary>  salaries = salaryRepo.findAllByDate(test);

        return returnCount(salaries);
    }
    @Transactional
    public Long findSalaryByYear() {
        LocalDate beginYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate endYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        List<Salary> salaries = salaryRepo.findAllByDateBetween(beginYear, endYear);
        return returnCount(salaries);
    }


    @Transactional
    public Long returnCount(List<Salary> salaries) {
        Long count = 0L;
        for (Salary salary : salaries) {
            count += salary.getSalary();
        }
        return count;
    }
}
