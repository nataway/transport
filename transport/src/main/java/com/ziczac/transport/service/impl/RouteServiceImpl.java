/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service.impl;

import com.ziczac.transport.entity.Route;
import com.ziczac.transport.repository.RouteComplexityRepository;
import com.ziczac.transport.repository.RouteRepository;
import com.ziczac.transport.service.RouteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class RouteServiceImpl implements RouteService {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private RouteComplexityRepository complexityRepository;

    @Override
    public List<Route> getAllRoutes(Pageable pageable) {
        return routeRepository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Route> getRoute(Integer id) {
        return routeRepository.findById(id);
    }

    @Override
    public Route saveRoute(Route route, int routeComplexityId) {
        route.setRouteComplexity(complexityRepository.findById(routeComplexityId).get());
        return routeRepository.save(route);
    }

    @Override
    public String deleteRoute(Integer id) {
        routeRepository.deleteById(id);
        return "Route with id: " + id + " deleted successfully!";
    }

    @Override
    public int totalItem() {
        return (int) routeRepository.count();
    }
    
}
