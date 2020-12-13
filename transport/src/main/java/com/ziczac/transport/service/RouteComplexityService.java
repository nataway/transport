/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service;

import com.ziczac.transport.entity.RouteComplexity;
import java.util.List;
import java.util.Optional;

public interface RouteComplexityService {
    List<RouteComplexity> getAllRouteComplexity();
    Optional<RouteComplexity> getRouteComplexity(Integer id);
    RouteComplexity saveRouteComplexity(RouteComplexity routeComplexity);
    String deleteRouteComplexity(Integer id);
}
