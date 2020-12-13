/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.repository;

import com.ziczac.transport.entity.Driver;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface DriverRepository extends JpaRepository<Driver, Integer> {
    @Query("SELECT d FROM Driver d WHERE d.idCard = :idCard")
    public Driver findByIdCard(@Param("idCard") String idCard);
    
    @Query("SELECT d FROM Driver d "
            + "LEFT JOIN Tour t ON d.id = t.driver.id OR d.id = t.assistant.id "
            + "WHERE d.status = 1 AND (t.finishedAt IS NOT NULL OR (t.driver.id IS NULL OR t.assistant.id IS NULL))")
    public List<Driver> findAllAvailable();
    
    @Query("SELECT COUNT(d) FROM Driver d WHERE d.idCard=:idCard")
    public Long countIdCard(@Param("idCard") String idCard);
    
    @Query("SELECT COUNT(d) FROM Driver d WHERE d.licenseNum=:licenseNum")
    public Long countLicenseNum(@Param("licenseNum") String licenseNum);
    
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Driver d SET d.status=:status WHERE d.id=:driverId")
    public int setActiveDriver(@Param("status") Integer status, @Param("driverId") Integer driverId);
}
