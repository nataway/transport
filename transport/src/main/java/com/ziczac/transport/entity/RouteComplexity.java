/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "routecomplexity")// độ phức tạp của tuyến đường.
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RouteComplexity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "coefficients_salary")
    @NotNull(message = "Coefficient salary is required")
    private Float coefficientsSalary;
    @Basic(optional = false)
    @Column(name = "coefficients_route")
    @NotNull(message = "Coefficient route is required")
    private Float coefficientsRoute;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeComplexity")
    @JsonIgnore
    private List<Route> routeList;

}
