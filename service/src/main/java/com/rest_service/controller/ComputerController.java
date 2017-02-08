package com.rest_service.controller;

import com.rest_service.model.Computer;
import com.rest_service.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value="/service")
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @RequestMapping(value="computers", method= RequestMethod.GET)
    public ResponseEntity<Collection<Computer>> getComputers() {
        Collection<Computer> computers = computerService.findAll();
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }
}
