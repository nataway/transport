/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ziczac.transport.exception;


public class IdCardNotUniqueException extends RuntimeException {

    public IdCardNotUniqueException() {
        super("Id card already exists");
    }
    
}