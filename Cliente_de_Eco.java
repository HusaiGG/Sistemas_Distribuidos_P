
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

public class Cliente_de_Eco {

    public static void main(String[] args) {

        //Host del servidor
        final String HOST = "127.0.0.1";
        //Puerto del servidor
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;

        try {
            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);
            Scanner out_tec = new Scanner(System.in);

            
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            while(true){

                int numEntero = out_tec.nextInt();

                //System.out.println("Se va a mandar el numero: "+numEntero);
                String numCadena= String.valueOf(numEntero);
                out.writeUTF(numCadena);

                //Desconectar cliente - si es 0
                if(numEntero == 0){
                        //break;
                    sc.close();
                    System.out.println("Me desconecté");
                    System.exit(1);
                }else{
                    //Recibo el mensaje del servidor
                    String mensaje = in.readUTF();
                    System.out.println(mensaje);
                }
                    
            }
            //System.out.println("Recibiendo mensaje... ");
            

            //Cerrando socket
            /*
            sc.close();
            System.out.println("Me desconecté");
*/
            

            }catch (IOException ex) {
            Logger.getLogger(Cliente_de_Eco.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}