package com.project.chinazess.repo;

import com.project.chinazess.models.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusRepo extends JpaRepository<Bonus, Long> {


}
