package com.rest_app.repository;

import com.rest_app.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    Collection<Computer> findByMakeLikeIgnoreCase(String make);
    Collection<Computer> findByModelLikeIgnoreCase(String model);
    Collection<Computer> findByProcessorLikeIgnoreCase(String model);
}
