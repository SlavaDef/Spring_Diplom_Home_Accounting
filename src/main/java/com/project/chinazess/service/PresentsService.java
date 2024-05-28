package com.project.chinazess.service;

import com.project.chinazess.models.Another;
import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Presents;
import com.project.chinazess.models.Salary;
import com.project.chinazess.repo.PresentsRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PresentsService {

    private PresentsRepo repo;

    @Transactional
    public void addPresent(Presents presents) {
        //  presents.setDate(LocalDate.now());
        repo.save(presents);

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
    public Long findPresentsByWeek() {
        return returnCount(repo.findAllDatesByWeek());
    }

    @Transactional
    public Long findPresentsByMonth() {
        return returnCount(repo.findAllDatesByMonth());
    }

    @Transactional
    public Long findPresentsByYear() {
        LocalDate beginYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate endYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        List<Presents> presents = repo.findAllByDateBetween(beginYear, endYear);
        return returnCount(presents);
    }

    @Transactional
    public List<Presents> findAllPresentsByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        return repo.findAllByDate(test);
    }


    @Transactional
    public void deletePresent(Presents present) {
        repo.delete(present);
    }

    @Transactional
    public Presents getPresentById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public void updatePresent(Presents present) {
        repo.save(present);
    }

    @Transactional(readOnly = true)
    public long count() {
        return repo.count();
    }

    @Transactional
    public List<Presents> allPageablePresentsByDate(LocalDate date, Pageable pageable) {
        Page<Presents> page = date == null

                ? repo.findAll(pageable)
                : repo.findAllByDate(date, pageable);

        return page.getContent();
    }

    @Transactional
    public List<Integer> getListOfPresentsPages() {
        long totalCount = count();
        long res = (totalCount / 6 + ((totalCount % 6 > 0) ? 1 : 0));
        List<Integer> numb = new ArrayList<>();
        for (int i = 0; i < res; i++) {
            numb.add(i);
        }
        return numb;
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
