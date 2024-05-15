package com.project.chinazess.service;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.repo.BonusRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BonusService {

    private BonusRepo repo;

    @Transactional
    public Bonus addBonus(Bonus bonus) {
       //  bonus.setDate(LocalDate.now());
        return repo.save(bonus);
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
    public Long findBonusByYear() {
        LocalDate beginYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate endYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        List<Bonus> bonuses = repo.findAllByDateBetween(beginYear, endYear);
        return returnCount(bonuses);
    }

    @Transactional
    public Long findBonusByMonth() {
        return returnCount(repo.findAllDatesByMonth());
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
