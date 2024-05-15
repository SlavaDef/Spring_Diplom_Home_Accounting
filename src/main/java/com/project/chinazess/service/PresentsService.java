package com.project.chinazess.service;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Presents;
import com.project.chinazess.models.Salary;
import com.project.chinazess.repo.PresentsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PresentsService {

    private PresentsRepo repo;

    @Transactional
    public Presents addPresent(Presents presents) {
        presents.setDate(LocalDate.now());
        return repo.save(presents);

    }


    @Transactional
    public Long getPresentCount() {
        Long count = 0L;
        List<Presents> presents = repo.findAll();

        for (Presents present : presents) {
            count += present.getPresents();
        }
        return count;
    }

    @Transactional
    public Long findBonusByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        Long count = 0L;
        List<Presents>  presents = repo.findAllByDate(test);
        for (Presents pre : presents) {
            count += pre.getPresents();
        }
        return count;
    }
}
