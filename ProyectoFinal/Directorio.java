import java.io.Serializable;

// must implement Serializable in order to be sent
public class Directorio implements Serializable{
    private final String ip;
    private final int puerto;

    public Directorio(String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
    }

    public String getIp() {
        return ip;
    }

    public int getPuerto() {
        return puerto;
    }
}