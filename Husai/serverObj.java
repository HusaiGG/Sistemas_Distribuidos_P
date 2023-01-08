import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class serverObj {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Uso desde consola:  <numero puerto>");

            System.exit(1);
        }

        int numeroPuerto = Integer.parseInt(args[0]);// convertimos el numero de puerto
        Socket socketdelCliente = null;
        DataInputStream in;
        DataOutputStream out;

        HashMap<Integer, byte[]> map1 = new HashMap<Integer, byte[]>();
        HashMap<Integer, byte[]> map2 = new HashMap<Integer, byte[]>();

        try {
            ServerSocket socketdelServidor = new ServerSocket(Integer.parseInt(args[0])); // escuchando peticiones
            System.out.println("Servidor iniciado");

            // System.out.println("Recuperando HASHMAP");

            FileInputStream fis = new FileInputStream("hashmap_par.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map1 = (HashMap) ois.readObject();
            ois.close();
            fis.close();

            FileInputStream fis2 = new FileInputStream("hashmap_impar.ser");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            map2 = (HashMap) ois2.readObject();
            ois2.close();
            fis2.close();

            // System.out.println("Hashmap_par size = " + map1.size());
            // System.out.println("Hashmap_impar size = " + map2.size());

            // System.out.println("\n\nHashMap PAR");
            // for (Integer key : map1.keySet()) {
            // System.out.println(key);
            // }
            // System.out.println("\n\nHashMap IMPAR");
            // for (Integer key2 : map2.keySet()) {
            // System.out.println(key2);
            // }

            while (true) {
                socketdelCliente = socketdelServidor.accept(); // se acepta la peticion
                System.out.println("Cliente conectado");

                in = new DataInputStream(socketdelCliente.getInputStream());
                out = new DataOutputStream(socketdelCliente.getOutputStream());

                String mensaje = "";
                // Leyendo mensaje del cliente
                mensaje = in.readUTF();
                System.out.println(mensaje);
                // Mandando mensaje
                out.writeUTF("Hola desde el servidor");

                // Mandando banderas de fragmento1
                out.writeInt(map1.size());

                ObjectOutputStream objout = new ObjectOutputStream(socketdelCliente.getOutputStream());

                ObjetoTrama vector[] = new ObjetoTrama[map1.size()];
                // Print values
                int i = 0;
                int lecturasig = 0;
                // System.out.println("creando objetos");
                // Creando y mandando Objeto a tráves del socket

                for (Integer key : map1.keySet()) {
                    ObjetoTrama datos = new ObjetoTrama(key, map1.get(key));
                    i++;
                    objout.writeObject(datos);
                    // lecturasig = in.readInt();
                    Thread.sleep(1000);
                }
                out.writeInt(map2.size());
                // Creando Arreglo de objetos
                ObjetoTrama vector2[] = new ObjetoTrama[map2.size()];
                i = 0;
                // System.out.println("crenado Objetos ddel array 2");
                for (Integer key : map2.keySet()) {
                    ObjetoTrama datos = new ObjetoTrama(key, map2.get(key));
                    i++;
                    objout.writeObject(datos);
                }

                mensaje = in.readUTF();
                System.out.println(mensaje);

                socketdelCliente.close();
                System.out.println("Cliente desconectado");
            }

        } catch (IOException e) {
            System.out.println(" ocurrio una excepcion cuando intentamos escuchar "
                    + numeroPuerto + " o esperando por una conexicon");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
