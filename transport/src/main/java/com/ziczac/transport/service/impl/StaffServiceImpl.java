/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service.impl;

import com.ziczac.transport.entity.Staff;
import com.ziczac.transport.repository.StaffRepository;
import com.ziczac.transport.service.StaffService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StaffServiceImpl implements StaffService {
    
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaffs(Pageable pageable) {
        return staffRepository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Staff> getStaff(Integer id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff updateStaff(int id, Staff staff, int status) {
        Staff staffUpdate = staffRepository.getOne(id);
        staffUpdate.setName(staff.getName());
        staffUpdate.setAddress(staff.getAddress());
        staffUpdate.setPhone(staff.getPhone());
        staffUpdate.getUser().setStatus(status);
        return staffRepository.save(staffUpdate);
    }

    @Override
    public String deleteStaff(Integer id) {
        staffRepository.deleteById(id);
        return "Staff with id: " + id + " deleted successfully!";
    }

    @Override
    public int totalItem() {
        return (int) staffRepository.count();
    }
    
}
