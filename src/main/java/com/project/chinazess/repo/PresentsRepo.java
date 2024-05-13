package com.project.chinazess.repo;

import com.project.chinazess.models.Presents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentsRepo extends JpaRepository<Presents, Long> {



}
