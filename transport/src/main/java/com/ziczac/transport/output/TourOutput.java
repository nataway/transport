/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.output;

import com.ziczac.transport.entity.Tour;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourOutput {
    private int page;
    private int totalPages;
    private List<Tour> resultList = new ArrayList<>();
}
