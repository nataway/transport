/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service.impl;

import com.ziczac.transport.entity.RouteComplexity;
import com.ziczac.transport.repository.RouteComplexityRepository;
import com.ziczac.transport.service.RouteComplexityService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RouteComplexityServiceImpl implements RouteComplexityService {
    @Autowired
    private RouteComplexityRepository complexityRepository;

    @Override
    public List<RouteComplexity> getAllRouteComplexity() {
        return complexityRepository.findAll();
    }

    @Override
    public Optional<RouteComplexity> getRouteComplexity(Integer id) {
        return complexityRepository.findById(id);
    }

    @Override
    public RouteComplexity saveRouteComplexity(RouteComplexity routeComplexity) {
        return complexityRepository.save(routeComplexity);
    }

    @Override
    public String deleteRouteComplexity(Integer id) {
        complexityRepository.deleteById(id);
        return "Route complexity with id: " + id + " deleted successfully!";
    }
    
}
