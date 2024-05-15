package com.project.chinazess.repo;

import com.project.chinazess.models.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BonusRepo extends JpaRepository<Bonus, Long> {

   // @Query(value = "SELECT count (c.bonus) FROM Bonus c WHERE c.date == 2024-01-02")
  //  Long findBonusByDate();

    List<Bonus> findAllByDate(LocalDate date);

    List<Bonus> findAllByDateBetween(LocalDate date, LocalDate date2);

}
