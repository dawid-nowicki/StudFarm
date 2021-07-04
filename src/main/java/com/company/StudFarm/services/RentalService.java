package com.company.StudFarm.services;

import com.company.StudFarm.entities.Horse;
import com.company.StudFarm.entities.Rental;
import com.company.StudFarm.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository repository;

    @Autowired
    public RentalService(RentalRepository repository) {
        this.repository = repository;
    }

    public List<Rental> GetAllByEmail(String email){
        List<Rental> list = repository.findAll();
        LinkedList<Rental> rentals = new LinkedList<>();
        for(Rental rental : list){
            if(rental.getCustomer().getEmail().equals(email))
                rentals.add(rental);
        }
        return rentals;
    }
    public List<Date> GetFreeDatesByHorse(Horse horse){
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTime().getTime());
        List<Rental> list = repository.getAllByDateAfterAndHorse(today, horse);
        LinkedList<Date> freeDates = new LinkedList<>();
        for (int i= 1;i<31; i++){
            calendar.add(Calendar.DATE, 1);
            freeDates.add(new Date(calendar.getTime().getTime()));
        }
        LinkedList<Date> dates = new LinkedList<>();
        for (Rental rental : list){
            freeDates.removeIf(date -> (date.toString().equals(rental.getDate().toString())));
        }

        return freeDates;
    }
    public Rental GetByDateAndHorse(Date date, Horse horse){
        return repository.getByDateAndHorse(date, horse);
    }
    public Rental AddRental(Rental rental){
        return repository.save(rental);
    }
}
