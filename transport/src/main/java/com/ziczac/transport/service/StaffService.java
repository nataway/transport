/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service;

import com.ziczac.transport.entity.Staff;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;


public interface StaffService {
    List<Staff> getAllStaffs(Pageable pageable);
    Optional<Staff> getStaff(Integer id);
    Staff saveStaff(Staff staff);
    Staff updateStaff(int id, Staff staff, int status);
    String deleteStaff(Integer id);
    int totalItem();
}
