import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServHilo extends Thread {

    private String ip;
    private int port;
    private List<byte[]> tramas;
    private int banderas[];

    public ServHilo(String serverIP, int serverPort, List<byte[]> lisTramas, int marcadores[]) {
        this.ip = new String(serverIP);
        this.port = serverPort;
        this.tramas = lisTramas;
        this.banderas = marcadores;
    }
    //Mandan objetos
    private void startServHilo() {
        try {
            int indicador = 0;
            // need host and port, we want to connect to the ServerSocket at port 7777
            Socket socket = new Socket(ip, port);
            System.out.println("Connected!");

            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create an object output stream from the output stream so we can send an object through it
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // make a bunch of messages to send.
            List<Trama> trama = new ArrayList<>();
            for (byte[] s:tramas) {
				trama.add(new Trama(s, banderas[indicador]));
                indicador ++;
			}  
            System.out.println("Sending messages to the ServerSocket");
            objectOutputStream.writeObject(trama);

            System.out.println("Closing socket and terminating program.");
            socket.close();
            } catch (IOException ex) {
                ex.getStackTrace();
            }
    }

    @Override
    public void run() {

        startServHilo();

        super.run();
    }
}
