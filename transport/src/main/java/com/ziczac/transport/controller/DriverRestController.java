/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.controller;

import com.ziczac.transport.service.DriverService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;
import com.ziczac.transport.output.DriverOutput;
import com.ziczac.transport.entity.Driver;
import com.ziczac.transport.repository.DriverRepository;

@RestController
@RequestMapping("/api/drivers")
public class DriverRestController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverRepository driverRepository;
    
    // findAll
    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Driver>> getAllDriver(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        DriverOutput result = new DriverOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        result.setResultList(driverService.getAllDrivers(pageable));
        result.setTotalPages((int) Math.ceil((double) (driverService.totalItem()) / limit));
        if (result.getResultList().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getResultList(), HttpStatus.OK);
    }

    // findAllAvailable
    @GetMapping(value = "/available", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Driver>> getAllDriverAvailable() {
        return new ResponseEntity<>(driverService.getAllDriversAvailable(), HttpStatus.OK);
    }

    // getPageNum
    @GetMapping(value = "/total-pages")
    public ResponseEntity<Integer> getPageNum(@RequestParam("limit") int limit) {
        DriverOutput result = new DriverOutput();
        result.setTotalPages((int) Math.ceil((double) (driverService.totalItem()) / limit));
        if (result.getTotalPages() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getTotalPages(), HttpStatus.OK);
    }

    // findById
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Driver> getDriver(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(driverService.getDriver(id).get(), HttpStatus.OK);
    }

    // findByIdCard
    @GetMapping(value = "/driver", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Driver> getDriver(@RequestParam("idCard") String idCard) {
        Driver driver = driverService.getDriverByIdCard(idCard);
        
        if (driver == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    // create
    @PostMapping
    public ResponseEntity<String> addDriver(@RequestBody @Valid Driver driver, UriComponentsBuilder builder) {
        String message = driverService.saveDriver(driver);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}")
                .buildAndExpand(driver.getId()).toUri());
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

//    // Counta
//    @GetMapping
//    public ResponseEntity<Long> count(@RequestBody @Valid Driver driver) {
//        Long count = driverRepository.countIdCard(driver.getIdCard());
//        return new ResponseEntity<>(count, HttpStatus.CREATED);
//    }

    // update
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateDriver(@RequestBody @Valid Driver driver, @PathVariable("id") Integer id) {
        String message = driverService.updateDriver(id, driver);
        
        if (message == null)
            return new ResponseEntity<>("MESSAGE NULL", HttpStatus.NO_CONTENT);
        
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    // update driver's status
    @PutMapping(value = "/{id}/update-status")
    public ResponseEntity<String> updateStatusDriver(@PathVariable("id") Integer id) {
        String message = driverService.updateStatusDriver(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//            return ex.getBindingResult()
//                    .getAllErrors().stream()
//                    .map(ObjectError::getDefaultMessage)
//                    .collect(Collectors.toList());
//    }
    
}
