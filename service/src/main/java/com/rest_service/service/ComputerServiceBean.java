package com.rest_service.service;

import com.rest_service.model.Computer;
import com.rest_service.repository.ComputerRepository;
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
}
