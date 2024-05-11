package com.project.chinazess.repo;

import com.project.chinazess.models.Plus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlusRopo extends JpaRepository<Plus, Long> {
}
