package com.company.StudFarm.services;

import com.company.StudFarm.entities.Horse;
import com.company.StudFarm.repositories.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorseService {
    private final HorseRepository repository;

    @Autowired
    public HorseService(HorseRepository repository) {
        this.repository = repository;
    }

    public List<Horse> GetAll(){
        return repository.findAll();
    }
    public boolean ExistsById(int id){
        return repository.existsById(id);
    }

}
