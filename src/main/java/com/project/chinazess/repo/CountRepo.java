package com.project.chinazess.repo;

import com.project.chinazess.models.Count;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountRepo extends JpaRepository<Count, Long> {
}
