/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "license_plate")
    @NotBlank(message = "License plate is required")
    @Pattern(regexp = "^[0-9]{2}[A-Z]{1}-[0-9]{3}\\.[0-9]{2}$", message = "License plate must be formatted like 30E-123.45")
    private String licensePlate;
    @Basic(optional = false)
    @Column(name = "color")
    @NotBlank(message = "Color is required")
    private String color;
    @Basic(optional = false)
    @Column(name = "manufacturer")
    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;
    @Basic(optional = false)
    @Column(name = "car_life")
    @NotNull(message = "Car life is required")
    @Min(value=1990, message="Car life must be equal or greater than 1990")
    private Integer carLife;
    @Basic(optional = false)
    @Column(name = "model")
    @NotBlank(message = "Model is required")
    private String model;
    @Basic(optional = false)
    @Column(name = "thumbnail")
    private String thumbnail;
    @Basic(optional = false)
    @Column(name = "seats_num")
    @NotNull(message = "Seats number is required")
    private Integer seatsNum;
    @Basic(optional = false)
    @Column(name = "years_of_use")
    @NotNull(message = "Years of use is required")
    @Min(value=0, message="Year of use must be equal or greater than 0")
    private Integer yearsOfUse;
    @Column(name = "last_maintenance")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastMaintenance;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "status")
    private Integer status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    @JsonIgnore
    private List<Tour> tourList;
    
    public Float getHealth() {
        float kmWorked = 0; // Tổng số km làm việc
        
        for (Tour tour : tourList) {
            // Phải kiểm tra xem bảo dưỡng hôm nào thì tính Tour từ hôm sau đó
            if (tour.getFinishedAt().before(lastMaintenance))
                continue;
            
            // Tính tổng bằng cách cộng dồn km làm việc (km làm việc = km thực * hệ số độ khó của tuyến đường(%))
            kmWorked += (tour.getRoute().getRouteLength() * tour.getRoute().getRouteComplexity().getCoefficientsRoute() / 100);
        }
        
        float lostDays = kmWorked / 100; // Số ngày hao = Tổng km làm việc / 100 (Vì Sau 100 km làm việc là hao 1 ngày)
        
        return ((360 - lostDays) / 360) * 100; // Trả về máu của xe (Tức số phần trăm xe còn sống <=> Giống thanh máu trong game =)))
    }
    
}
