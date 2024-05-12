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


    @Transactional
   public Long getCount(){
       // Count count = new Count();
       // count.setSalaries(salRepo.findAll());
      return service.getSalaryCount();

        }



    @Transactional
    public void addCount(Count count){
       repo.save(count);
    }


}
