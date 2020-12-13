/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service;

import com.ziczac.transport.entity.Vehicle;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface VehicleService {
    List<Vehicle> getAllVehicles(Pageable pageable);
    List<Vehicle> getAllVehiclesAvailable();
    BigInteger getRevenueBetweenDate(Integer id, Date start, Date end);
    Optional<Vehicle> getVehicle(Integer id);
    String saveVehicle(Vehicle vehicle);
    String updateVehicle(Integer id, Vehicle vehicle);
    String updateVehicleLastMaintenance(Integer id);
    String updateStatusVehicle(Integer id);
    int totalItem();
}
