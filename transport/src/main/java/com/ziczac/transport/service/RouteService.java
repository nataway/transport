/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service;

import com.ziczac.transport.entity.Route;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface RouteService {
    List<Route> getAllRoutes(Pageable pageable);
    Optional<Route> getRoute(Integer id);
    Route saveRoute(Route route, int routeComplexityId);
    String deleteRoute(Integer id);
    int totalItem();
}
