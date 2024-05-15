package com.project.chinazess.repo;

import com.project.chinazess.models.Bonus;
import com.project.chinazess.models.Presents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PresentsRepo extends JpaRepository<Presents, Long> {

    List<Presents> findAllByDate(LocalDate date);

}
