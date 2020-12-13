/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.controller;

import com.ziczac.transport.service.VehicleService;
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
import com.ziczac.transport.output.VehicleOutput;
import com.ziczac.transport.entity.Vehicle;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleRestController {
    @Autowired
    private VehicleService vehicleService;
    
    // findAll
    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Vehicle>> getAllVehicle(@RequestParam("page") int page, @RequestParam("limit") int limit) {
    	VehicleOutput result = new VehicleOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        result.setResultList(vehicleService.getAllVehicles(pageable));
        result.setTotalPages((int) Math.ceil((double) (vehicleService.totalItem()) / limit));
        if (result.getResultList().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getResultList(), HttpStatus.OK);
    }

    // findAllAvailable
    @GetMapping(value = "/available", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Vehicle>> getAllVehicleAvailable() {
        return new ResponseEntity<>(vehicleService.getAllVehiclesAvailable(), HttpStatus.OK);
    }

    // getPageNum
    @GetMapping(value = "/total-pages")
    public ResponseEntity<Integer> getPageNum(@RequestParam("limit") int limit) {
    	VehicleOutput result = new VehicleOutput();
        result.setTotalPages((int) Math.ceil((double) (vehicleService.totalItem()) / limit));
        if (result.getTotalPages() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getTotalPages(), HttpStatus.OK);
    }

    // findById
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(vehicleService.getVehicle(id).get(), HttpStatus.OK);
    }

    // calculate revenue between 2 dates = Tính doanh thu giữa 2 ngày
    @GetMapping(value = "/{id}/calculate-revenue", produces = "application/json;charset=UTF-8")
    public ResponseEntity<BigInteger> getRevenueBetweenDate(@PathVariable("id") Integer id, @RequestParam String start, @RequestParam String end) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        BigInteger revenue = null;
        try {
            revenue = vehicleService.getRevenueBetweenDate(id, sdf.parse(start), sdf.parse(end));
        } catch (ParseException ex) {
            Logger.getLogger(VehicleRestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<>(revenue, HttpStatus.OK);
    }

    // create
    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody @Valid Vehicle vehicle, UriComponentsBuilder builder) {
    	String message = vehicleService.saveVehicle(vehicle);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}")
                .buildAndExpand(vehicle.getId()).toUri());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    // update
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable("id") Integer id, @RequestBody @Valid Vehicle vehicle) {
    	String message = vehicleService.updateVehicle(id, vehicle);
        
        if (message == null)
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // update last maintenance
    @PutMapping(value = "/{id}/update-last-maintenance")
    public ResponseEntity<String> updateVehicle(@PathVariable("id") Integer id) {
    	String message = vehicleService.updateVehicleLastMaintenance(id);
        
        if (message == null)
            return new ResponseEntity<>(message, HttpStatus.NO_CONTENT);
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    // delete
    @PutMapping(value = "/{id}/update-status")
    public ResponseEntity<String> updateStatusVehicle(@PathVariable("id") Integer id) {
        String message = vehicleService.updateStatusVehicle(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
}
