package com.project.chinazess.repo;

import com.project.chinazess.models.Another;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnotherRepo extends JpaRepository<Another, Long> {

    List<Another> findAllByDate(LocalDate date);
    List<Another> findAllByDateBetween(LocalDate date, LocalDate date2);
}
