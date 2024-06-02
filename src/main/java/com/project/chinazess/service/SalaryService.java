package com.project.chinazess.service;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Salary;
import com.project.chinazess.repo.SalaryRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SalaryService {


    private final SalaryRepo salaryRepo;

    @Transactional
    public void addSalary(Salary salary) {
        // salary.setDate(LocalDate.now());
        salaryRepo.save(salary);
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
        List<Salary> salaries = salaryRepo.findAllByDate(test);

        return returnCount(salaries);
    }

    @Transactional
    public Long findSalaryByWeek() {
        return returnCount(salaryRepo.findAllDatesByWeek());
    }

    @Transactional
    public Long findSalaryByMonth() {
        return returnCount(salaryRepo.findAllDatesByMonth());
    }


    @Transactional
    public Long findSalaryByYear() {
        LocalDate beginYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate endYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        List<Salary> salaries = salaryRepo.findAllByDateBetween(beginYear, endYear);
        return returnCount(salaries);
    }

    @Transactional
    public List<Salary> findAllSalaryByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        return salaryRepo.findAllByDate(test);
    }

    @Transactional
    public void deleteSalary(Salary salary) {
        salaryRepo.delete(salary);
    }

    @Transactional
    public Salary getSalaryById(Long id) {
        return salaryRepo.findById(id).orElseThrow();
    }

    @Transactional
    public void updateSalary(Salary salary) {
        salaryRepo.save(salary);
    }

    @Transactional(readOnly = true)
    public long count() {
        return salaryRepo.count();
    }

    @Transactional
    public List<Salary> allPageableSalaryByDate(LocalDate date, Pageable pageable) {
        Page<Salary> page = date == null

                ? salaryRepo.findAll(pageable)
                : salaryRepo.findAllByDate(date, pageable);

        return page.getContent();
    }

    @Transactional
    public List<Salary> bonusByWeek(Pageable pageable) {

        Page<Salary> page = salaryRepo.findAllDatesByWeek(pageable);

        if (page == null) {
            page = salaryRepo.findAll(pageable);
        }

        return page.getContent();
    }

    @Transactional
    public List<Integer> getListOfSalaryPages() {
        long totalCount = salaryRepo.count();
        long res = (totalCount / 6 + ((totalCount % 6 > 0) ? 1 : 0));
        List<Integer> numb = new ArrayList<>();
        for (int i = 0; i < res; i++) {
            numb.add(i);
        }
        return numb;
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
