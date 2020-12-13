/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.controller;

import com.ziczac.transport.service.RouteComplexityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;
import com.ziczac.transport.entity.RouteComplexity;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/routecomplexities")
public class RouteComplexityRestController {
    @Autowired
    private RouteComplexityService complexityService;
    
    // findAll
    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<RouteComplexity>> getAllComplexities() {
        return new ResponseEntity<>(complexityService.getAllRouteComplexity(), HttpStatus.OK);
    }

    // findById
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<RouteComplexity> getComplexity(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(complexityService.getRouteComplexity(id).get(), HttpStatus.OK);
    }

    // create
    @PostMapping
    public ResponseEntity<RouteComplexity> addComplexity(@RequestBody @Valid RouteComplexity routeComplexity, UriComponentsBuilder builder) {
        RouteComplexity routeComplexityCreated = complexityService.saveRouteComplexity(routeComplexity);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}")
                .buildAndExpand(routeComplexityCreated.getId()).toUri());
        return new ResponseEntity<>(routeComplexityCreated, HttpStatus.CREATED);
    }

    // update
    @PutMapping(value = "/{id}")
    public ResponseEntity<RouteComplexity> updateComplexity(@RequestBody @Valid RouteComplexity routeComplexity) {
        return new ResponseEntity<>(complexityService.saveRouteComplexity(routeComplexity), HttpStatus.OK);
    }
    
    // delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteComplexity(@PathVariable("id") Integer id) {
        String message = complexityService.deleteRouteComplexity(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
