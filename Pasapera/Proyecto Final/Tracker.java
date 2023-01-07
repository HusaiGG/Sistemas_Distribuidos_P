import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Tracker {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Tracker tracker = new Tracker();
        //Manda peticion de archivo
        List<Message> lista = tracker.recibeIP();
        lista.forEach((msg)-> System.out.println("Esto es en el main " + msg.getText() + " " + msg.getArchivo()));
        //Manda directorio de instancias
        Thread.sleep(1000);
        tracker.enviaDirectorio();
        
    }

    public List recibeIP() throws IOException, ClassNotFoundException{
        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from " + socket + "!");
    
        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
    
        // read the list of messages from the socket
        List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
        System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);
        // print out the text of every message
        System.out.println("All messages:");
        listOfMessages.forEach((msg)-> System.out.println(msg.getText() + " " + msg.getArchivo()));
    
        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
        
        return listOfMessages;
    }
    
    public void enviaDirectorio() throws IOException, ClassNotFoundException{
        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Connected!");
    
        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
    
        // make a bunch of messages to send.
        //Agrego instancias que ya estaban descargando
        List<Directorio> directorio = new ArrayList<>();
        directorio.add(new Directorio("192.168.0.93", 8888));
        directorio.add(new Directorio("192.168.0.61", 8888));

        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(directorio);
        
        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
}


