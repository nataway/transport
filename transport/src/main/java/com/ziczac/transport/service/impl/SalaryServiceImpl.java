/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service.impl;

import com.ziczac.transport.entity.Salary;
import com.ziczac.transport.repository.SalaryRepository;
import com.ziczac.transport.service.SalaryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public List<Salary> getAllSalaries(Pageable pageable) {
        return salaryRepository.findAll(pageable).getContent();
    }

    @Override
    public Optional<Salary> getSalary(Integer id) {
        return salaryRepository.findById(id);
    }

    @Override
    public Salary saveSalary(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public String deleteSalary(Integer id) {
        salaryRepository.deleteById(id);
        return "Salary complexity with id: " + id + " deleted successfully!";
    }

    @Override
    public int totalItem() {
        return (int) salaryRepository.count();
    }
    
}
