/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicacionescritoriop8;

import java.util.Scanner;

/**
 *
 * @author husai
 */
public class AplicacionEscritorioP8 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws InterruptedException {
        Scanner lectura = new Scanner (System.in);
        
        System.out.println("Ingresa el Id del producto a comprar:");
        int Id = lectura.nextInt();
        System.out.println("Precio del producto: ");
        float precio = lectura.nextFloat();
        System.out.println("Cantidad del producto: ");
        int cantidad = lectura.nextInt();
        
        int monto = (int)comprar(Id, precio, cantidad);
        
        //System.out.println("El total de la compra es de: "+monto);
        
        System.out.println("Detalles de compra: ");
        System.out.println("ID\tPrecio\t\tCantidad\tTotal");
        System.out.println(Id+"\t"+precio+"\t\t"+cantidad+"\t\t"+monto);
        
        //System.out.println("Confirmando datos...");
        Thread.sleep(2000);
        
        System.out.println("Ingresa los datos de tu tarjeta para finalizar la transacción ");
        
        String Num_card = "";
        
        do{
            System.out.println("<Numero de tarjeta (16 digitos)> ");
            Num_card = lectura.next();    
           
        }while(Num_card.length() != 16);

        System.out.println("<Nombre>");
        String nombre = lectura.next();
        System.out.println("<código CVV>");
        int cvv = lectura.nextInt();
        
        
        boolean rest = pagar(Num_card, monto, nombre, cvv);
        
        System.out.println("Verificando datos de la tarjeta...");
        Thread.sleep(2000);
        
        if(rest == false){
            System.out.println("[ERROR]: Fallo la transacción");
        }else{
            System.out.println("[SUCCESS]: Transacion exitosa");
        }
        
    }

    private static boolean pagar(java.lang.String numeroTarjeta, int monto, java.lang.String nombre, int codigoCVV) {
        org.me.practica8.Practica8WS_Service service = new org.me.practica8.Practica8WS_Service();
        org.me.practica8.Practica8WS port = service.getPractica8WSPort();
        return port.pagar(numeroTarjeta, monto, nombre, codigoCVV);
    }

    private static float comprar(int idProducto, float precio, int numProductos) {
        org.me.practica8.Practica8WS_Service service = new org.me.practica8.Practica8WS_Service();
        org.me.practica8.Practica8WS port = service.getPractica8WSPort();
        return port.comprar(idProducto, precio, numProductos);
    }
    
}
