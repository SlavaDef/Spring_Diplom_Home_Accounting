package com.project.chinazess.service;

import com.project.chinazess.models.Count;
import com.project.chinazess.models.Salary;
import com.project.chinazess.repo.CountRepo;
import com.project.chinazess.repo.SalaryRepo;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountService {

    private CountRepo repo;
    private SalaryRepo salRepo;
    private SalaryService service;
    private BonusService bonusService;
    private PresentsService presentsService;


    @Transactional
    public Long getCount() { // return count from all entityes

        return service.getSalaryCount() + bonusService.getBonusCount() + presentsService.getPresentCount();

    }

    @Transactional
    public Count getCountById(Long id) {
        return repo.getReferenceById(id);
    }


    @Transactional
    public void addCount(Count count) {
        repo.save(count);
    }


}
