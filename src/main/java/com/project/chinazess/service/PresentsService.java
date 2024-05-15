package com.project.chinazess.service;

import com.project.chinazess.models.Another;
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
        return returnCount(repo.findAll());
    }

    @Transactional
    public Long findPresentsByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        return returnCount(repo.findAllByDate(test));
    }
    @Transactional
    public Long findPresentsByYear() {
        LocalDate beginYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate endYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        List<Presents> presents = repo.findAllByDateBetween(beginYear, endYear);
        return returnCount(presents);
    }


    @Transactional
    public Long returnCount(List<Presents> presents) {
        Long count = 0L;
        for (Presents pr : presents) {
            count += pr.getPresents();
        }
        return count;
    }
}
