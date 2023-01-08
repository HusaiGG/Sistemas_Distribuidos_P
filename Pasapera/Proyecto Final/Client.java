import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread {

    private String ip;
    private int port;

    public Client(String serverIP, int serverPort) {
        this.ip = new String(serverIP);
        this.port = serverPort;
    }

    private void startClient() {
        int señal = 0;
        try {
            Socket socket = new Socket(ip, port); 
            if (señal == 0){
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Ingrese dato...");
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            out.writeInt(n);
            señal = 2;
            }
            else{
            //Cambiar a recibir objetos
            /*DataInputStream in = new DataInputStream(socket.getInputStream());
            long tiempo = in.readLong();
            System.out.println(tiempo);
            socket.close();*/
            try {
                for (int i = 0; i<= 6; i++){
                    ServerSocket ss = new ServerSocket(8888);
                    socket = ss.accept();
                    InputStream inputStream = socket.getInputStream();
                    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                    // read the list of messages from the socket
                    Trama tramaReciv = (Trama) objectInputStream.readObject();
                    System.out.println("Received [" + tramaReciv.getTramo() + "] messages from: " + socket);
                    // print out the text of every message
                    List<Trama> listOfTrama = new ArrayList<>();
                    listOfTrama.add(tramaReciv);
                    System.out.println("All messages:");
                    listOfTrama.forEach((msg)-> System.out.println(msg.getTramo() + " Y su bandera: " + msg.getBandera()));
                    System.out.println("Closing sockets.");
                }
            }
            catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }catch (Exception e) {
            
        }
    }

    @Override
    public void run() {


            startClient();
            super.run();
    
    }
}
