package com.project.chinazess.service;

import com.project.chinazess.models.Another;
import com.project.chinazess.repo.AnotherRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AnotherService {

    private AnotherRepo repo;

    @Transactional
    public Another addAnother(Another  another ) {
      //  another.setDate(LocalDate.now());
        return repo.save(another);
    }

    @Transactional
    public Long getAnotherCount() {
        return returnCount(repo.findAll());
    }

    @Transactional
    public Long findAnotherByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        return returnCount(repo.findAllByDate(test));
    }

    @Transactional
    public Long findAnotherByYear() {
        LocalDate beginYear = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        LocalDate endYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        List<Another> another = repo.findAllByDateBetween(beginYear, endYear);
        return returnCount(another);
    }

    @Transactional
    public Long findByAnotherMonth() {
        return returnCount(repo.findAllDatesByMonth());
    }


    @Transactional
    public Long returnCount(List<Another> anothers) {
        Long count = 0L;
        for (Another another : anothers) {
            count += another.getAnother();
        }
        return count;
    }
}
