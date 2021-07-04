package com.company.StudFarm.repositories;

import com.company.StudFarm.entities.Horse;
import com.company.StudFarm.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    List<Rental> getAllByDateAfterAndHorse(Date date, Horse horse);
    Rental getByDateAndHorse(Date date, Horse horse);
}
