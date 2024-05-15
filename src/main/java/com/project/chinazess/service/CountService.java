package com.project.chinazess.service;

import com.project.chinazess.models.Count;

import com.project.chinazess.repo.CountRepo;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CountService {

    private CountRepo repo;
    private SalaryService salaryService;
    private BonusService bonusService;
    private PresentsService presentsService;
    private AnotherService anotherService;


    @Transactional
    public Long getCount() { // return count from all entityes

        return salaryService.getSalaryCount() + bonusService.getBonusCount()
                + presentsService.getPresentCount() + anotherService.getAnotherCount();

    }

    @Transactional
    public Count getCountById(Long id) {
        return repo.getReferenceById(id);
    }

    @Transactional
    public Count getCountById2(Long id) {
        return repo.findById(id).get();
    }


    @Transactional
    public void addCount(Count count) {
        repo.save(count);
    }

    @Transactional
    public void updateCount(Count count) {

        repo.save(count);
    }

    @Transactional
    public Long getCountByDay() { // return count by day from all entityes

        return bonusService.findBonusByToday() + anotherService.findAnotherByToday()
                + salaryService.findSalaryByToday() + presentsService.findPresentsByToday();

    }

    @Transactional
    public Long getCountByYear() { // return count by year from all entityes

        return bonusService.findBonusByYear()+ anotherService.findAnotherByYear()
                + salaryService.findSalaryByYear() + presentsService.findPresentsByYear();

    }

}
