package com.rest_app.service;

import com.rest_app.model.Computer;
import com.rest_app.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ComputerServiceBean implements ComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    @Override
    public Collection<Computer> findAll() {
        Collection<Computer> computers = computerRepository.findAll(sortByIdAsc());
        return computers;
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
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

    @Override
    public Collection<Computer> findByMakeLike(String make) {
        Collection<Computer> computersByMake = computerRepository.findByMakeLikeIgnoreCase(make);
        return computersByMake;
    }

    @Override
    public Collection<Computer> findByModelLike(String model) {
        Collection<Computer> computersByModel = computerRepository.findByModelLikeIgnoreCase(model);
        return computersByModel;
    }

    @Override
    public Collection<Computer> findByProcessorLike(String processor) {
        Collection<Computer> computersByProcessor = computerRepository.findByProcessorLikeIgnoreCase(processor);
        return computersByProcessor;
    }
}
