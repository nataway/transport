/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service.impl;

import com.ziczac.transport.entity.Tour;
import com.ziczac.transport.entity.Driver;
import com.ziczac.transport.entity.Salary;
import com.ziczac.transport.exception.DriverDuplicateException;
import com.ziczac.transport.exception.PassengerOverloadException;
import com.ziczac.transport.repository.DriverRepository;
import com.ziczac.transport.repository.RouteRepository;
import com.ziczac.transport.repository.SalaryRepository;
import com.ziczac.transport.repository.TourRepository;
import com.ziczac.transport.repository.VehicleRepository;
import com.ziczac.transport.service.TourService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TourServiceImpl implements TourService {
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public List<Tour> getAllTours(Pageable pageable) {
        return tourRepository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Tour> getTour(Integer id) {
        return tourRepository.findById(id);
    }

    @Override
    public Tour saveTour(Tour tour, int driverId, int assistantId, int vehicleId, int routeId) {
        tour.setDriver(driverRepository.findById(driverId).get());
        tour.setAssistant(driverRepository.findById(assistantId).get());
        tour.setVehicle(vehicleRepository.findById(vehicleId).get());
        tour.setRoute(routeRepository.findById(routeId).get());
        
        if (tour.getDriver().equals(tour.getAssistant()))
            throw new DriverDuplicateException();
        
        if (tour.getPassengersNum() > tour.getVehicle().getSeatsNum() - 2)
            throw new PassengerOverloadException();
        
        return tourRepository.save(tour);
    }

    @Override
    public String deleteTour(Integer id) {
        tourRepository.deleteById(id);
        return "Tour with id: " + id + " deleted successfully!";
    }

    @Override
    public int totalItem() {
        return (int) tourRepository.count();
    }

    @Override
    public Tour updateTour(Tour tour) { // This function used to update driver salary after each finished tour
        // Update tour.finished_at
        tour.setFinishedAt(new Timestamp(System.currentTimeMillis()));
        
        // Calculate total fare
        BigInteger totalFare = calculateTotalFare(tour);
        
        // Calculate driver and assistant's salary that depends on RouteComplexity.coefficent_salary
        double coefficentSalary = tour.getRoute().getRouteComplexity().getCoefficientsSalary() / 100;
        BigInteger salary = BigDecimal.valueOf(convertToDouble(totalFare) * coefficentSalary).toBigInteger(); // Convert between double and big integer
        
        // Get tour started_at and finished_at -> prepare to save on salary table
        Date from_date = tour.getStartedAt();
        Date to_date = tour.getFinishedAt();
        
        // Get driver and assistant from this tour
        Driver driver = tour.getDriver();
        Driver assistant = tour.getAssistant();
        
        // Save to salary table
        Salary driverSalary = new Salary(salary, from_date, to_date, driver, tour);
        Salary assistantSalary = new Salary(BigDecimal.valueOf(convertToDouble(salary) / 2).toBigInteger(), from_date, to_date, assistant, tour);
        
        salaryRepository.save(driverSalary);
        salaryRepository.save(assistantSalary);
//        List<Salary> salaryList = new ArrayList<>();
//        salaryList.add(driverSalary);
//        salaryList.add(assistantSalary);
//        
//        tour.setSalaryList(salaryList);
        
        return tourRepository.save(tour);
    }
    
    private static double convertToDouble(BigInteger bigInteger) {
        double d = bigInteger.doubleValue();
        if (d == Double.POSITIVE_INFINITY) {
            return Long.MAX_VALUE;
        } else if (d == Double.NEGATIVE_INFINITY) {
            return Long.MIN_VALUE;
        }
        return bigInteger.doubleValue();
    }
    
    protected static BigInteger calculateTotalFare(Tour tour) {
        double fare = convertToDouble(tour.getFare());
        BigInteger totalFare = BigDecimal.valueOf(fare * tour.getPassengersNum()).toBigInteger(); // Convert between double and big integer
        return totalFare;
    }
    
}
