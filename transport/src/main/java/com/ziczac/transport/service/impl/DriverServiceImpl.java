/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service.impl;

import com.ziczac.transport.entity.Driver;
import com.ziczac.transport.entity.Vehicle;
import com.ziczac.transport.exception.IdCardNotUniqueException;
import com.ziczac.transport.exception.LicenseNumNotUniqueException;
import com.ziczac.transport.repository.DriverRepository;
import com.ziczac.transport.service.DriverService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;

    @Override
    public List<Driver> getAllDrivers(Pageable pageable) {
        return driverRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Driver> getAllDriversAvailable() {
        return driverRepository.findAllAvailable();
    }

    @Override
    public Optional<Driver> getDriver(Integer id) {
        return driverRepository.findById(id);
    }

    @Override
    public Driver getDriverByIdCard(String idCard) {
        return driverRepository.findByIdCard(idCard);
    }

    @Override
    public String saveDriver(Driver driver) {
        if (driverRepository.countIdCard(driver.getIdCard()) > 0)
            throw new IdCardNotUniqueException();
        
        if (driverRepository.countLicenseNum(driver.getLicenseNum()) > 0)
            throw new LicenseNumNotUniqueException();
        
        driverRepository.save(driver);
        return "Driver with name: " + driver.getName() + " added successfully!";
    }

    @Override
    public String updateDriver(Integer id, Driver driver) {
    	Driver getDriver = driverRepository.findById(id).get();
    	
        if (driverRepository.countIdCard(driver.getIdCard()) > 0
        		&& !driver.getIdCard().equals(getDriver.getIdCard()))
            throw new IdCardNotUniqueException();
        
        if (driverRepository.countLicenseNum(driver.getLicenseNum()) > 0
        		&& !driver.getLicenseNum().equals(getDriver.getLicenseNum()))
            throw new LicenseNumNotUniqueException();
        
        driverRepository.save(driver);
        return "Driver with name: " + driver.getName() + " updated successfully!";
    }

    @Override
    public String updateStatusDriver(Integer id) {
    	Driver driver = driverRepository.findById(id).get();
        
        if (driver.getStatus() == 1)
            driverRepository.setActiveDriver(0, id);
        else
            driverRepository.setActiveDriver(1, id);
        
//        driverRepository.save(driver);
        return "Driver with id: " + id + " status updated successfully!";
    }

    @Override
    public int totalItem() {
        return (int) driverRepository.count();
    }
}
