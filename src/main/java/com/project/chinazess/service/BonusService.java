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
        bonus.setDate(LocalDate.now());
        return repo.save(bonus);
    }

    @Transactional
    public Long getBonusCount() {
        Long count = 0L;
        List<Bonus> bonuses = repo.findAll();
        for (Bonus bonus : bonuses ) {
            count += bonus.getBonus();
        }
        return count;
    }
}
