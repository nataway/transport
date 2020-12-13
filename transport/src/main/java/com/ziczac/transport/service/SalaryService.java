/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.service;

import com.ziczac.transport.entity.Salary;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface SalaryService {
    List<Salary> getAllSalaries(Pageable pageable);
    Optional<Salary> getSalary(Integer id);
    Salary saveSalary(Salary salary);
    String deleteSalary(Integer id);
    int totalItem();
}
