package com.rest_app.controller;

import com.rest_app.model.Computer;
import com.rest_app.service.ComputerService;
import com.rest_app.service.ExternalComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(value="/rest")
public class ComputerController {
    @Autowired
    private ComputerService computerService;
    @Autowired
    private ExternalComputerService externalComputerService;

    @RequestMapping(value="computers", method=RequestMethod.GET)
    public ResponseEntity<Collection<Computer>> getComputers() {
        Collection<Computer> computers = computerService.findAll();
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    @RequestMapping(value="computers/search", method=RequestMethod.GET, params="make")
    public ResponseEntity<Collection<Computer>> getComputersByMake(@RequestParam String make) {
        Collection<Computer> computers = computerService.findByMakeLike(make+"%");
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    @RequestMapping(value="computers/search", method=RequestMethod.GET, params="model")
    public ResponseEntity<Collection<Computer>> getComputersByModel(@RequestParam String model) {
        Collection<Computer> computers = computerService.findByModelLike(model+"%");
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }

    @RequestMapping(value="computers/search", method=RequestMethod.GET, params="processor")
    public ResponseEntity<Collection<Computer>> getComputersByProcessor(@RequestParam String processor) {
        Collection<Computer> computers = computerService.findByProcessorLike(processor+"%");
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

    @RequestMapping(value="computers", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
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

    @RequestMapping(value="external/computers", method=RequestMethod.GET)
    public ResponseEntity<Collection<Computer>> getExternalComputers() {
        Collection<Computer> computers = externalComputerService.findAllExternal();
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }
}
