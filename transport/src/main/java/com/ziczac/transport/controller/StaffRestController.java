/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.controller;

import com.ziczac.transport.entity.Staff;
import com.ziczac.transport.output.StaffOutput;
import com.ziczac.transport.service.StaffService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/staffs")
public class StaffRestController {
    @Autowired
    private StaffService staffService;
    
    // findAll
    @GetMapping(produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Staff>> getAllStaffs(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        StaffOutput result = new StaffOutput();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").descending());
        result.setResultList(staffService.getAllStaffs(pageable));
        result.setTotalPages((int) Math.ceil((double) (staffService.totalItem()) / limit));
        if (result.getResultList().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getResultList(), HttpStatus.OK);
    }

    // getPageNum
    @GetMapping(value = "/total-pages")
    public ResponseEntity<Integer> getPageNum(@RequestParam("limit") int limit) {
        StaffOutput result = new StaffOutput();
        result.setTotalPages((int) Math.ceil((double) (staffService.totalItem()) / limit));
        if (result.getTotalPages() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(result.getTotalPages(), HttpStatus.OK);
    }

    // findById
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Staff> getStaff(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(staffService.getStaff(id).get(), HttpStatus.OK);
    }

    // create
    @PostMapping
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff, UriComponentsBuilder builder) {
        Staff staffCreated = staffService.saveStaff(staff);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/{id}")
                .buildAndExpand(staff.getId()).toUri());
        return new ResponseEntity<>(staffCreated, HttpStatus.CREATED);
    }

    // update
    @PutMapping(value = "/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable("id") Integer id, @RequestBody Staff staff, @RequestParam("status") int status) {
        return new ResponseEntity<>(staffService.updateStaff(id, staff, status), HttpStatus.OK);
    }
    
    // delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable("id") Integer id) {
        String message = staffService.deleteStaff(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
