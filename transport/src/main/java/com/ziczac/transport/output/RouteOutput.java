/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.output;

import com.ziczac.transport.entity.Route;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteOutput {
    private int page;
    private int totalPages;
    private List<Route> resultList = new ArrayList<>();
}
