package com.company.StudFarm.repositories;

import com.company.StudFarm.entities.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Integer > {
    boolean existsById(int id);
}