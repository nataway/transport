/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.repository;

import com.ziczac.transport.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RouteRepository extends JpaRepository<Route, Integer> {
    
}
