package com.project.chinazess.repo;

import com.project.chinazess.models.Another;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnotherRepo extends JpaRepository<Another, Long> {
}
