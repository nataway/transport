/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ziczac.transport.validator.annotations.Adult;

import java.io.Serializable;
import java.math.BigInteger;
import org.joda.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.Months;

@Entity
@Table(name = "driver")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    
    @NotBlank(message = "Name is required") // yêu cầu không được để trống
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "id_card")
    @NotBlank(message = "Id card is required")
    private String idCard;
    @Basic(optional = false)
    @Column(name = "license_num")
    @NotBlank(message = "License number is required")
    private String licenseNum;
    @Basic(optional = false)
    @NotBlank(message = "License type is required")
    @Column(name = "license_type")
    private String licenseType;
    @Basic(optional = false)
    @NotBlank(message = "Address is required")
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth should be in the past")
    @Adult(message = "Driver must be over 18 years old")
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull(message = "Seniority is required")
    @Column(name = "seniority")//thâm niên
    private Integer seniority;
    @Basic(optional = false)
    @Column(name = "status")
    private Integer status;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    @JsonIgnore
    private List<Salary> salaryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "driver")
    @JsonIgnore
    private List<Tour> tourListAsDriver;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "assistant")
    @JsonIgnore
    private List<Tour> tourListAsAssistant;
    
//    public void removeSalary(Salary salary) {
//        this.getSalaryList().remove(salary);
//    }
// 
//    public void removeSalaries() {
//        for (Salary salary : salaryList) {
//            removeSalary(salary);
//        }
//    }
//
//    public void setNullDriverInTours() {
//    	if (tourListAsDriver.size() > 0)
//    		for (Tour tour : tourListAsDriver)
//    			tour.setDriver(null);
//        
//    	if (tourListAsAssistant.size() > 0)
//    		for (Tour tour : tourListAsAssistant)
//    			tour.setAssistant(null);
//    }
    
    public BigInteger getMonthSalaryTillNow() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int day = calendar.get(Calendar.DATE); // Get current day
        //Note: +1 the month for current month
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        
        LocalDate payrollThisMonth = new LocalDate(year, month, 15); // VD: 15/07/2020
        LocalDate payrollLastMonth = payrollThisMonth.minusMonths(1).minusDays(1); // VD: 14/06/2020
        LocalDate payrollNextMonth = payrollThisMonth.plusMonths(1); // VD: 15/08/2020
        
        BigInteger total = BigInteger.valueOf(0);
        
        if (day < 15) { // day < 15 -> still calculate salary since payroll date last month
            for (Salary salary : salaryList) {
                if (salary.getFromDate().after(payrollLastMonth.toDate()) 
                        && salary.getFromDate().before(payrollThisMonth.toDate())) {
                   total = total.add(salary.getSalary()); // total += salary
                }
            }
        } else {
            for (Salary salary : salaryList) {
                if (salary.getFromDate().after(payrollThisMonth.minusDays(1).toDate()) 
                        && salary.getFromDate().before(payrollNextMonth.toDate())) {
                   total = total.add(salary.getSalary()); // total += salary
                }
            }
        }
        
        return total;
    }

}
