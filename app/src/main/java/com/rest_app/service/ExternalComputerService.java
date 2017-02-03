package com.rest_app.service;

import com.rest_app.model.Computer;

import java.util.Collection;

/**
 * Created by Gregor on 3.02.2017.
 */
public interface ExternalComputerService {
    Collection<Computer> findAllExternal();
}
