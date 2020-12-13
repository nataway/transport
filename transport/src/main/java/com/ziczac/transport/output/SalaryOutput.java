/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.output;

import com.ziczac.transport.entity.Salary;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryOutput {
    private int page;
    private int totalPages;
    private List<Salary> resultList = new ArrayList<>();
}
