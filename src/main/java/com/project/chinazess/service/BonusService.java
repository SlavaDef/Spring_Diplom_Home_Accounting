package com.project.chinazess.service;

import com.project.chinazess.controller.AddController;
import com.project.chinazess.models.Bonus;
import com.project.chinazess.repo.BonusRepo;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BonusService {

   // private static final Logger LOGGER = LogManager.getLogger(AddController.class);

    private BonusRepo repo;

    @Transactional
    public void addBonus(Bonus bonus) {
       //  bonus.setDate(LocalDate.now());
         repo.save(bonus);
    }

    @Transactional
    public Long getBonusCount() {
        List<Bonus> bonuses = repo.findAll();
        return returnCount(bonuses);
    }

    @Transactional
    public Long findBonusByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        List<Bonus>  bonuses = repo.findAllByDate(test);
        return returnCount(bonuses);
    }

    @Transactional
    public Long findBonusByWeek() {
        return returnCount(repo.findAllDatesByWeek());
    }


    @Transactional
    public Long findBonusByMonth() {
        return returnCount(repo.findAllDatesByMonth());
    }

    @Transactional
    public Long findBonusByYear() {
        LocalDate beginYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate endYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        List<Bonus> bonuses = repo.findAllByDateBetween(beginYear, endYear);
        return returnCount(bonuses);
    }

    @Transactional
    public Long returnCount(List<Bonus> bonuses) {
        Long count = 0L;
        for (Bonus bonus : bonuses) {
            count += bonus.getBonus();
        }
        return count;
    }
}
