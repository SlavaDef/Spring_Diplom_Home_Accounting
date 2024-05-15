package com.project.chinazess.service;

import com.project.chinazess.models.Another;
import com.project.chinazess.models.Bonus;
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
        another.setDate(LocalDate.now());
        return repo.save(another);
    }

    @Transactional
    public Long getAnotherCount() {
        Long count = 0L;
        List<Another> anothers = repo.findAll();
        for (Another a : anothers ) {
            count += a.getAnother();
        }
        return count;
    }

    @Transactional
    public Long findAnotherByToday() {
        LocalDate today = LocalDate.now();
        LocalDate test = LocalDate.of(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        Long count = 0L;
        List<Another>  anothers = repo.findAllByDate(test);
        for (Another another : anothers) {
            count += another.getAnother();
        }
        return count;
    }
}
