package com.rest_app.service;

import com.rest_app.model.Computer;
import com.rest_app.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ComputerServiceBean implements ComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    @Override
    public Collection<Computer> findAll() {
        Collection<Computer> computers = computerRepository.findAll();
        return computers;
    }

    @Override
    public Computer find(Long id) {
        Computer computer = computerRepository.findOne(id);
        return computer;
    }

    @Override
    public Computer create(Computer computer) {
        if (computer.getId() != null) {
            //Cannot create Computer with existing ID
            return null;
        }
        Computer newComputer = computerRepository.save(computer);
        return newComputer;
    }

    @Override
    public Computer update(Computer computer) {
        Computer existingComputer = computerRepository.findOne(computer.getId());
        if (existingComputer == null) {
            //Cannot update nonexistent Computer
            return null;
        }
        Computer newComputer = computerRepository.save(computer);
        return newComputer;
    }

    @Override
    public void delete(Long id) {
        computerRepository.delete(id);
    }
}
