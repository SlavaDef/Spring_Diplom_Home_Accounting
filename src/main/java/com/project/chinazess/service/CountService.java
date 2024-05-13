package com.project.chinazess.service;

import com.project.chinazess.models.Count;
import com.project.chinazess.models.Salary;
import com.project.chinazess.repo.CountRepo;
import com.project.chinazess.repo.SalaryRepo;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountService {

    private CountRepo repo;
    private SalaryService service;
    private BonusService bonusService;
    private PresentsService presentsService;
    private AnotherService anotherService;


    @Transactional
    public Long getCount() { // return count from all entityes

        return service.getSalaryCount() + bonusService.getBonusCount()
                + presentsService.getPresentCount()+anotherService.getAnotherCount();

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



}
