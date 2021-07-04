package com.company.StudFarm.services;

import com.company.StudFarm.entities.Stable;
import com.company.StudFarm.repositories.StableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StableService {
    private final StableRepository repository;

    @Autowired
    public StableService(StableRepository repository) {
        this.repository = repository;
    }

    public List<Stable> GetAll(){
        return repository.findAll();
    }
}
