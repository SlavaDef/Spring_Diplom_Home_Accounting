package com.project.chinazess.service;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.repo.BonusRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@AllArgsConstructor
public class BonusService {

    // private static final Logger LOGGER = LogManager.getLogger(AddController.class);

    private BonusRepo repo;

    @Transactional
    public void addBonus(Bonus bonus) {
          bonus.setDate(LocalDate.now());
          bonus.setTime(LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute()));
        repo.save(bonus);
    }

    @Transactional
    public void addBonusForTest(Bonus bonus) {
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
        List<Bonus> bonuses = repo.findAllByDate(test);
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
    public List<Bonus> findAllBonusesByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        return repo.findAllByDate(test);
    }

    @Transactional
    public void deleteBonus(Bonus bonus) {
        repo.delete(bonus);
    }

    @Transactional
    public Bonus getBonusById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public void updateBonus(Bonus bonus) {
        repo.save(bonus);
    }


  /*  @Transactional(readOnly = true)
    public List<Bonus> findAll2(Pageable pageable) {

        return repo.findAll(pageable).getContent();
    } */

    @Transactional(readOnly = true)
    public long count() {
        return repo.count();
    }

    @Transactional
    public List<Bonus> bonusByDate(LocalDate date, Pageable pageable) {
        Page<Bonus> page = date == null

                ? repo.findAll(pageable)
                : repo.findAllByDate(date, pageable);

        return page.getContent();
    }

    @Transactional
    public List<Bonus> bonusByWeek(Pageable pageable) {

        Page<Bonus> page = repo.findAllDatesByWeek(pageable);

        if (page == null) {
            page = repo.findAll(pageable);
        }

        return page.getContent();
    }

    @Transactional
    public List<Bonus> bonusByMonth(Pageable pageable) {

        Page<Bonus> page = repo.findAllDatesByMonth(pageable);

        if (page == null) {
            page = repo.findAll(pageable);
        }

        return page.getContent();
    }

    @Transactional
    public List<Bonus> bonusByYear(Pageable pageable) {
        LocalDate beginDate =
                LocalDate.of(LocalDate.now().getYear(), 1, 1);


        LocalDate endDate = LocalDate.of(LocalDate.now().getYear(), 12, 31);

        Page<Bonus> page = repo.findAllByDateBetween(beginDate,endDate,pageable);

        if (page == null) {
            page = repo.findAll(pageable);
        }

        return page.getContent();
    }


    @Transactional
    public List<Integer> getListOfBonusPages() {
        long totalCount = repo.count();
        long result = (totalCount / 6 + ((totalCount % 6 > 0) ? 1 : 0));
        List<Integer> numb = new ArrayList<>();
        for (int i = 0; i < result; i++) {
            numb.add(i);
        }
        return numb;
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
