/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salary")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "salary")
    private BigInteger salary;
    @Basic(optional = false)
    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Basic(optional = false)
    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    
    @ManyToOne(optional = false)
    @JsonIgnore
    private Driver driver;
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Tour tour;

    public Salary(BigInteger salary, Date fromDate, Date toDate, Driver driver, Tour tour) {
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.driver = driver;
        this.tour = tour;
    }

    public String getDriverName() {
        return getDriver().getName();
    }
}
