import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread {


    ExecutorService threadPool = Executors.newCachedThreadPool();
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Server servidor = new Server();
        servidor.startServer();
            
    }

    
    //Private class
    class ClientSocket implements Runnable {
    
        private Socket m_socket;
        int confirmacion;

        
        ClientSocket(Socket sock) {
            m_socket = sock;
        }
    
        @Override
        //Recibe los objetos
        public void run() {
            int control = 0;
            int escuchando = 0;
            List<Trama> listadeTramas = new ArrayList<>();
            if (control == 0){
                listadeTramas = recibirServidorWeb();
                control = 1;
            }
            else{
                while(escuchando == 0){
                    escuchando = escuchar(); 
                }
                mandarFragmentos(listadeTramas);

            }
            
        }

        public List recibirServidorWeb(){
            List<Trama> listadeTramas = new ArrayList<>();
            try {
                InputStream inputStream = m_socket.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                // read the list of messages from the socket
                List<Trama> listOfTrama = (List<Trama>) objectInputStream.readObject();
                System.out.println("Received [" + listOfTrama.size() + "] messages from: " + m_socket);
                // print out the text of every message
                System.out.println("All messages:");
                listOfTrama.forEach((msg)-> System.out.println(msg.getTramo() + " Y su bandera: " + msg.getBandera()));
                System.out.println("Closing sockets.");
                listadeTramas = listOfTrama;
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return listadeTramas; 
        }

        public int escuchar(){
            int señal = 0;
            try {
                DataInputStream in = new DataInputStream(m_socket.getInputStream());
                System.out.println("Digite un entero:");
                int n = in.readInt();
                señal = n;
                System.out.println(señal);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            return señal;
        }

        public void mandarFragmentos(List<Trama> fragmentos){
            System.out.println("Connected!");
            // get the output stream from the socket.
            OutputStream outputStream = m_socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // make a bunch of messages to send.
            /*List<Trama> trama = new ArrayList<>();*/
            for (Trama d: fragmentos){
                Trama mensaje = new Trama(d.getTramo(),d.getBandera());
                System.out.println("Sending messages to the ServerSocket");
                objectOutputStream.writeObject(mensaje);
                Thread.sleep(1000);
            }
            
            System.out.println("Closing socket and terminating program.");
        }
    
    }
    
    
    @Override
    public void run() {
        startServer();
        super.run();
    }
    
    public void startServer() {


        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("Esperando Conexion");
    
            do {
                Socket socket = ss.accept();
                threadPool.execute(new ClientSocket(socket));

                ss.close();
                System.out.println("Conexión terminada");
            } while(true);
    
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}