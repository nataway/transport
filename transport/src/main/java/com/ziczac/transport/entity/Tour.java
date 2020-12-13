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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tour")
@EntityListeners(AuditingEntityListener.class)
public class Tour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "passengers_num")
    @NotNull(message = "Passenger number is required")
    @Min(value=1, message="Number of passengers must be equal or greater than 1")
    private Integer passengersNum;
    @Basic(optional = false)
    @Column(name = "fare")
    @NotNull(message = "Fare is required")
    private BigInteger fare;
    @Column(name = "started_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date startedAt;
    @Column(name = "finished_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishedAt;
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Driver driver;
    @JoinColumn(name = "assistant_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Driver assistant;
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Vehicle vehicle;
    @JoinColumn(name = "route_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Route route;
    @OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, mappedBy = "tour")
    private List<Salary> salaryList;
    
//    @Transient
//    private String driverName;
//    @Transient
//    private String assistantName;
//    @Transient
//    private String vehicleModel;

    public String getDriverName() {
        return getDriver().getName();
    }

    public String getAssistantName() {
        return getAssistant().getName();
    }

    public String getVehicleModel() {
        return getVehicle().getModel();
    }

    public Integer getVehicleSeatsNum() {
        return getVehicle().getSeatsNum();
    }
    
}
