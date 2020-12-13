/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.repository;

import com.ziczac.transport.entity.Vehicle;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Query("SELECT v FROM Vehicle v "
            + "LEFT JOIN Tour t ON v.id = t.vehicle.id "
            + "WHERE v.status = 1 AND (t.vehicle.id IS NULL OR t.finishedAt IS NOT NULL)")
    public List<Vehicle> findAllAvailable();
    
    @Query("SELECT COUNT(v) FROM Vehicle v WHERE v.licensePlate=:licensePlate")
    public Long countLicensePlate(@Param("licensePlate") String licensePlate);
}
