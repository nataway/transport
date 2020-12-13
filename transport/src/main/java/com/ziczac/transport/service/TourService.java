/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service;

import com.ziczac.transport.entity.Tour;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface TourService {
    List<Tour> getAllTours(Pageable pageable);
    Optional<Tour> getTour(Integer id);
    Tour saveTour(Tour tour, int driverId, int assistantId, int vehicleId, int routeId);
    String deleteTour(Integer id);
    int totalItem();
    Tour updateTour(Tour tour);
}
