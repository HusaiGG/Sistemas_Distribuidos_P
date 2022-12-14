
package org.me.practica8;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.5
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Practica8WS", targetNamespace = "http://practica8.me.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Practica8WS {


    /**
     * 
     * @param monto
     * @param numeroTarjeta
     * @param nombre
     * @param codigoCVV
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "pagar", targetNamespace = "http://practica8.me.org/", className = "org.me.practica8.Pagar")
    @ResponseWrapper(localName = "pagarResponse", targetNamespace = "http://practica8.me.org/", className = "org.me.practica8.PagarResponse")
    @Action(input = "http://practica8.me.org/Practica8WS/pagarRequest", output = "http://practica8.me.org/Practica8WS/pagarResponse")
    public boolean pagar(
        @WebParam(name = "numero_tarjeta", targetNamespace = "")
        String numeroTarjeta,
        @WebParam(name = "monto", targetNamespace = "")
        int monto,
        @WebParam(name = "nombre", targetNamespace = "")
        String nombre,
        @WebParam(name = "codigo_CVV", targetNamespace = "")
        int codigoCVV);

    /**
     * 
     * @param precio
     * @param idProducto
     * @param numProductos
     * @return
     *     returns float
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "comprar", targetNamespace = "http://practica8.me.org/", className = "org.me.practica8.Comprar")
    @ResponseWrapper(localName = "comprarResponse", targetNamespace = "http://practica8.me.org/", className = "org.me.practica8.ComprarResponse")
    @Action(input = "http://practica8.me.org/Practica8WS/comprarRequest", output = "http://practica8.me.org/Practica8WS/comprarResponse")
    public float comprar(
        @WebParam(name = "id_producto", targetNamespace = "")
        int idProducto,
        @WebParam(name = "precio", targetNamespace = "")
        float precio,
        @WebParam(name = "num_productos", targetNamespace = "")
        int numProductos);

}
