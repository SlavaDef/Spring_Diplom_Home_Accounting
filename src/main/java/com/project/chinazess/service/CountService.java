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

    @Transactional
   public Long getCount(){
       return repo.findById(1L).get().getCount();   }

    @Transactional
    public void addCount(Count count){
       repo.save(count);
    }


}
