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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "route")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "entry_point")
    @NotBlank(message = "Entry point is required")
    private String entryPoint;
    @Basic(optional = false)
    @Column(name = "end_point")
    @NotBlank(message = "End point is required")
    private String endPoint;
    @Basic(optional = false)
    @Column(name = "route_length")
    @NotNull(message = "Route length is required")
    @Min(value=1, message="Route length must be equal or greater than 1km")
    private Float routeLength;
    @JoinColumn(name = "routecomplexity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RouteComplexity routeComplexity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "route")
    @JsonIgnore
    private List<Tour> tourList;

}
