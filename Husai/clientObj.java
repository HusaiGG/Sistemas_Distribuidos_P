import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class clientObj {
    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println(
                    "Uso desde consola: java Cliente_de_Eco <nombre de host (computadora)> <numero puerto>");
            System.exit(1);
        }

        String nombreHost = args[0];
        int numeroPuerto = Integer.parseInt(args[1]);
        DataInputStream in;
        DataOutputStream out;
        try {
            Socket socketEco = new Socket(nombreHost, numeroPuerto);

            in = new DataInputStream(socketEco.getInputStream());
            out = new DataOutputStream(socketEco.getOutputStream());
            int banderas = 0;
            String mensaje = "";

            // Mensaje inicial
            out.writeUTF("Hola desde el cliente");
            mensaje = in.readUTF();

            // Mandando Banderas
            banderas = in.readInt();

            // Leyendo Banderas del fragmento1
            System.out.println(banderas);
            int i = 0;
            ObjectInputStream objin = new ObjectInputStream(socketEco.getInputStream());
            ObjetoTrama vector[] = new ObjetoTrama[banderas];

            // Creando Objetos
            while (i < banderas) {
                vector[i] = (ObjetoTrama) objin.readObject();
                i++;
            }

            // System.out.println("Se terminó de copiar los fragmentos");

            // Leyendo banderas del archivo2
            banderas = in.readInt();
            // System.out.println("Banderas leídas: " + banderas);
            System.out.println("El tamaño del archivo que se va a transferir es de " + banderas + "MB");
            Thread.sleep(1000);

            ObjetoTrama vector2[] = new ObjetoTrama[banderas];
            // Leyendo segundo lista de fragmentos

            // Vista de carga documento
            String simbolo = "##";
            String descarga = "";
            int porcentaje = 0;
            System.out.println(porcentaje + "%");

            // verificar abnderas para continuar ahí

            Integer[] verificacion = {};
            i = verificacion.length;

            while (i < banderas) {
                vector2[i] = (ObjetoTrama) objin.readObject();
                i++;
                porcentaje = (i * 100) / banderas;
                descarga = descarga + simbolo;
                System.out.println(descarga + " " + porcentaje + "%");
                // out.write(1);
                Thread.sleep(1000);
            }
            out.writeUTF("Se recibieron todos los paquetes");
            socketEco.close();

            // Copiando a Archivo;
            FileOutputStream fos3 = new FileOutputStream("vid3.mp4");
            int cont = 0;
            byte[] contenedor = null;

            while (cont != vector.length) {
                fos3.write(vector[cont].getTrama());
                fos3.write(vector2[cont].getTrama());
                cont++;
            }
            fos3.close();
            System.out.println("Archivo Completo");

        } catch (UnknownHostException e) {
            System.err.println("No conozco al host " + nombreHost);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("no se pudo obtener E/S para la conexion " +
                    nombreHost);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void porcentajeDescarga(int banderas) {
    }

}
