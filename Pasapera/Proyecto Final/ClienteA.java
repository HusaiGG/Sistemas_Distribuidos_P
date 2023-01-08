import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.net.InetAddress;
import java.net.NetworkInterface;


public class ClienteA {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        int i = 0;
        String[] ip_s = new String[2];
        ClienteA clienteA = new ClienteA();
        //envio datos de ip y archivo solicitado
        clienteA.enviaIP();
        Thread.sleep(1000);
        List<Directorio> directorio = clienteA.recibe();
        directorio.forEach((msg)-> System.out.println("En el main " + msg.getIp() + " " + msg.getPuerto()));
        for (Directorio d:directorio){
            ip_s[i] = d.getIp();
            i ++;
        }
        System.out.println("\nEsta es la IP del cliente1:" + ip_s[0] + "\nEsta es la IP del cliente2: " + ip_s[1]);
        Client client1 = new Client(ip_s[0], 8888);
        Client client2 = new Client(ip_s[1], 8888);

        client1.start();
        System.out.println("El hilo del cliente1 se ha iniciado");
        client2.start();
<<<<<<< HEAD
        System.out.println("El hilo del cliente2 se ha iniciado");
        /*client3.start();*/
=======
>>>>>>> refs/remotes/origin/main

        try {
            client1.join();
            System.out.println("Esperando a que el hilo del cliente1 muera");

        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("un hilo interrumpió el hilo del cliente1");
        }
        try {
            client2.join();
            System.out.println("Esperando a que el hilo del cliente1 muera");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
      /* try {
            client3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("No ha muerto ningun hilo");
=======

        
>>>>>>> refs/remotes/origin/main
    }

    public List recibe() throws IOException, ClassNotFoundException{
        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket;
        socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from socket nuevo!");
    
        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
    
        // read the list of messages from the socket
        List<Directorio> listOfIP = (List<Directorio>) objectInputStream.readObject();
        System.out.println("Received [" + listOfIP.size() + "] messages from: " + socket);
        // print out the text of every message
        System.out.println("All messages:");
        listOfIP.forEach((msg)-> System.out.println(msg.getIp() + msg.getPuerto()));
    
        System.out.println("Closing sockets by recibe().");
        ss.close();
        socket.close();
        
        return listOfIP;
    }
    
    public void enviaIP() throws IOException, ClassNotFoundException{
        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 7777); //Aqui cambiar localhost por IP de computadora de Tracker
        System.out.println("Connected!");
    
        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        //Obtener la ip local
        String ip;
        ip = " ";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;
    
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                    System.out.println(iface.getDisplayName() + " " + ip);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } 
        
        // make a bunch of messages to send.
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("archivo solicitado", ip)); //cambiar atributo texto por el nombre del archivo a solicitar

        System.out.println("Sending messages to the ServerSocket");
        objectOutputStream.writeObject(messages);
    
        System.out.println("Closing socket and terminating program by enviaIP().");
        socket.close();
    }
}


