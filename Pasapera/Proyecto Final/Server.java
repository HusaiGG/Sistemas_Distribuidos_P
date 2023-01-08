import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {


    ExecutorService threadPool = Executors.newCachedThreadPool();
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Server servidor = new Server();
        servidor.startServer();
            
    }

    
    //Private class
    class ClientSocket implements Runnable {
    
        private Socket m_socket;

        
        ClientSocket(Socket sock) {
            m_socket = sock;
        }
    
        @Override
        public void run() {
            System.out.println("Inicio de run");
            try {
                DataInputStream in = new DataInputStream(m_socket.getInputStream());
                System.out.println("Digite un entero:");
                int n = in.readInt();
                long time = n;
                DataOutputStream out = new DataOutputStream(m_socket.getOutputStream());
                out.writeLong(time);
                out.flush();
                System.out.println(time);
                System.out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
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