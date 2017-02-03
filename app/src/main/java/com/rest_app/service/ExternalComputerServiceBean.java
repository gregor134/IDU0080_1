package com.rest_app.service;

import com.rest_app.model.Computer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;

@Component
public class ExternalComputerServiceBean implements ExternalComputerService {

    @Override
    public Collection<Computer> findAllExternal() {
        RestTemplate restTemplate = new RestTemplate();
        Computer[] computers = restTemplate.getForObject("http://localhost:8080/service/computers", Computer[].class);
        return Arrays.asList(computers);
    }
}
