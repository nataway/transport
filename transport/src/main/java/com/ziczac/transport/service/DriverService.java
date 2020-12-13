/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service;

import com.ziczac.transport.entity.Driver;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;


public interface DriverService {
    List<Driver> getAllDrivers(Pageable pageable);
    List<Driver> getAllDriversAvailable();
    Optional<Driver> getDriver(Integer id);
    Driver getDriverByIdCard(String idCard);
    String saveDriver(Driver driver);
    String updateDriver(Integer id, Driver driver);
    String updateStatusDriver(Integer id);
    int totalItem();
}
