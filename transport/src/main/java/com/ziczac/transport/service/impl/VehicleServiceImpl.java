/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service.impl;

import com.ziczac.transport.entity.Tour;
import com.ziczac.transport.entity.Vehicle;
import com.ziczac.transport.exception.LicensePlateNotUniqueException;
import com.ziczac.transport.repository.VehicleRepository;
import com.ziczac.transport.service.VehicleService;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles(Pageable pageable) {
        return vehicleRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Vehicle> getAllVehiclesAvailable() {
        return vehicleRepository.findAllAvailable();
    }

    @Override
    public Optional<Vehicle> getVehicle(Integer id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public String saveVehicle(Vehicle vehicle) {
        if (vehicleRepository.countLicensePlate(vehicle.getLicensePlate()) > 0)
            throw new LicensePlateNotUniqueException();
        
        vehicleRepository.save(vehicle);
        return "Vehicle with model: " + vehicle.getModel() + " saved successfully!";
    }

    @Override
    public String updateVehicle(Integer id, Vehicle vehicle) {
    	Vehicle getVehicle = vehicleRepository.findById(id).get();
    	
        if (vehicleRepository.countLicensePlate(vehicle.getLicensePlate()) > 0 
        		&& !vehicle.getLicensePlate().equals(getVehicle.getLicensePlate()))
            throw new LicensePlateNotUniqueException();
        
        vehicleRepository.save(vehicle);
        return "Vehicle with model: " + vehicle.getModel() + " updated successfully!";
    }

    @Override
    public String updateStatusVehicle(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).get();
        
        if (vehicle.getStatus() == 1)
            vehicle.setStatus(0);
        else
            vehicle.setStatus(1);
        
        vehicleRepository.save(vehicle);
        return "Vehicle with id: " + id + " updated status successfully!";
    }

    @Override
    public int totalItem() {
        return (int) vehicleRepository.count();
    }

    @Override
    public BigInteger getRevenueBetweenDate(Integer id, Date start, Date end) {
        LocalDate lcstart = new LocalDate(start);
        LocalDate lcend = new LocalDate(end);
        Vehicle vehicle = vehicleRepository.findById(id).get();
        BigInteger total = BigInteger.valueOf(0);
        for (Tour tour : vehicle.getTourList()) {
            if (tour.getFinishedAt() != null 
                    && lcstart.minusDays(1).toDate().before(tour.getStartedAt()) 
                    && lcend.plusDays(1).toDate().after(tour.getFinishedAt())) {
                // Calculate total fare of a tour
                BigInteger totalFare = TourServiceImpl.calculateTotalFare(tour);
                total = total.add(totalFare);
            }
        }
        return total;
    }

    @Override
    public String updateVehicleLastMaintenance(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).get();
        vehicle.setLastMaintenance(new Timestamp(System.currentTimeMillis()));
        
        vehicleRepository.save(vehicle);
        return "Vehicle with model: " + vehicle.getModel() + " has been successfully maintained!";
    }

}
