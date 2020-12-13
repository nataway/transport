/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.controller;

import com.ziczac.transport.service.RouteService;
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
import com.ziczac.transport.output.RouteOutput;
import com.ziczac.transport.entity.Route;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/routes")
public class RouteRestController {
    @Autowired
    private RouteService routeService;
    
    // findAll
    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Route>> getAllRoute(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        RouteOutput result = new RouteOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        result.setResultList(routeService.getAllRoutes(pageable));
        result.setTotalPages((int) Math.ceil((double) (routeService.totalItem()) / limit));
        if (result.getResultList().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getResultList(), HttpStatus.OK);
    }

    // getPageNum
    @GetMapping(value = "/total-pages")
    public ResponseEntity<Integer> getPageNum(@RequestParam("limit") int limit) {
        RouteOutput result = new RouteOutput();
        result.setTotalPages((int) Math.ceil((double) (routeService.totalItem()) / limit));
        if (result.getTotalPages() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getTotalPages(), HttpStatus.OK);
    }

    // findById
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Route> getRoute(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(routeService.getRoute(id).get(), HttpStatus.OK);
    }

    // create
    @PostMapping
    public ResponseEntity<Route> addRoute(@RequestBody @Valid Route route, @RequestParam int routeComplexityId, UriComponentsBuilder builder) {
        Route routeCreated = routeService.saveRoute(route, routeComplexityId);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}")
                .buildAndExpand(routeCreated.getId()).toUri());
        return new ResponseEntity<>(routeCreated, HttpStatus.CREATED);
    }

    // update
    @PutMapping(value = "/{id}")
    public ResponseEntity<Route> updateRoute(@RequestBody @Valid Route route, @RequestParam int routeComplexityId) {
        Route routeUpdated = routeService.saveRoute(route, routeComplexityId);
        
        if (routeUpdated == null)
            return new ResponseEntity<>(route, HttpStatus.NO_CONTENT);
        
        return new ResponseEntity<>(routeUpdated, HttpStatus.OK);
    }
    
    // delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable("id") Integer id) {
        String message = routeService.deleteRoute(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
}
