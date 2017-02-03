package com.rest_service.service;

import com.rest_service.model.Computer;

import java.util.Collection;

public interface ComputerService {
    Collection<Computer> findAll();
    /*Computer find(Long id);
    Computer create(Computer computer);
    Computer update(Computer computer);
    void delete(Long id);*/
}
