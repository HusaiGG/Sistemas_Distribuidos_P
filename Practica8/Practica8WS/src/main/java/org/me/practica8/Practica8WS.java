/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package org.me.practica8;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author husai
 */
@WebService(serviceName = "Practica8WS")
public class Practica8WS {
    /**
     * Web service operation
     */
    @WebMethod(operationName = "pagar")
    public boolean pagar(@WebParam(name = "numero_tarjeta") String numero_tarjeta, @WebParam(name = "monto") int monto, @WebParam(name = "nombre") String nombre, @WebParam(name = "codigo_CVV") int codigo_CVV) {
        //TODO write your implementation code here:
        int total_card = 1000;
        total_card -= monto;

        if(total_card < 0){
            return false;
        }
        else{
            return true;
        } 
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "comprar")
    public float comprar(@WebParam(name = "id_producto") int id_producto, @WebParam(name = "precio") float precio, @WebParam(name = "num_productos") int num_productos) {
        //TODO write your implementation code here:

        float total = precio*num_productos;
        
        return total;
    }

    
}
