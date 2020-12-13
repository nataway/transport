/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.controller;

import com.ziczac.transport.service.TourService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import com.ziczac.transport.output.TourOutput;
import com.ziczac.transport.entity.Tour;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/tours")
public class TourRestController {
    @Autowired
    private TourService tourService;
    
    // findAll
    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Tour>> getAllTour(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        TourOutput result = new TourOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        result.setResultList(tourService.getAllTours(pageable));
        result.setTotalPages((int) Math.ceil((double) (tourService.totalItem()) / limit));
        if (result.getResultList().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getResultList(), HttpStatus.OK);
    }

    // getPageNum
    @GetMapping(value = "/total-pages")
    public ResponseEntity<Integer> getPageNum(@RequestParam("limit") int limit) {
        TourOutput result = new TourOutput();
        result.setTotalPages((int) Math.ceil((double) (tourService.totalItem()) / limit));
        if (result.getTotalPages() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getTotalPages(), HttpStatus.OK);
    }

    // findById
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Tour> getTour(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(tourService.getTour(id).get(), HttpStatus.OK);
    }

    // create
    @PostMapping
    public ResponseEntity<Tour> addTour(@RequestBody @Valid Tour tour, @RequestParam int driverId, 
            @RequestParam int assistantId, @RequestParam int vehicleId, 
            @RequestParam int routeId, UriComponentsBuilder builder) {
        Tour tourCreated = tourService.saveTour(tour, driverId, assistantId, vehicleId, routeId);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}")
                .buildAndExpand(tourCreated.getId()).toUri());
        return new ResponseEntity<>(tourCreated, HttpStatus.CREATED);
    }

    // update normal
    @PutMapping(value = "/{id}")
    public ResponseEntity<Tour> updateTour(@RequestBody @Valid Tour tour, @RequestParam int driverId, 
            @RequestParam int assistantId, @RequestParam int vehicleId, @RequestParam int routeId, @PathVariable("id") Integer id) {
        Tour tourUpdated = tourService.saveTour(tour, driverId, assistantId, vehicleId, routeId);
        
        if (tourUpdated == null)
            return new ResponseEntity<>(tour, HttpStatus.NO_CONTENT);
        
        return new ResponseEntity<>(tourUpdated, HttpStatus.OK);
    }
    
    // finish tour = update if tour is finished
    @PutMapping(value = "/finish-tour/{id}")
    public ResponseEntity<Tour> finishTour(@PathVariable("id") Integer id) {
        Tour tourUpdated = tourService.updateTour(tourService.getTour(id).get());
        
        if (tourUpdated == null)
            return new ResponseEntity<>(tourUpdated, HttpStatus.NO_CONTENT);
        
        return new ResponseEntity<>(tourUpdated, HttpStatus.OK);
    }
    
    // delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTour(@PathVariable("id") Integer id) {
        String message = tourService.deleteTour(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
}
