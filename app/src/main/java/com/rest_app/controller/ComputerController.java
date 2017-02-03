package com.rest_app.controller;

import com.rest_app.model.Computer;
import com.rest_app.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value="/app")
public class ComputerController {
    @Autowired
    private ComputerService computerService;

    @RequestMapping(value="computers", method= RequestMethod.GET)
    public ResponseEntity<Collection<Computer>> getComputers() {
        Collection<Computer> computers = computerService.findAll();
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    @RequestMapping(value="computer/{id}", method=RequestMethod.GET)
    public ResponseEntity<Computer> getComputer(@PathVariable("id") Long id) {
        Computer computer = computerService.find(id);
        if (computer == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(computer, HttpStatus.OK);
    }

    @RequestMapping(value="computers", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Computer> createComputer(@RequestBody Computer computer) {
        Computer savedComputer = computerService.create(computer);
        return new ResponseEntity<>(savedComputer, HttpStatus.CREATED);
    }

    @RequestMapping(value="computer/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Computer> updateComputer(@RequestBody Computer computer) {
        Computer updatedComputer = computerService.update(computer);
        if (updatedComputer == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="computer/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Computer> deleteComputer(@PathVariable("id") Long id) {
        computerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
