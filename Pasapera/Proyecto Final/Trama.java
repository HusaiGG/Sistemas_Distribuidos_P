import java.io.Serializable;

public class Trama implements Serializable{

    private final byte[] tramo;
    private final int bandera;

    public Trama(byte[] tramo, int bandera) {
        this.tramo = tramo;
        this.bandera = bandera;
    }

    public byte[] getTramo() {
        return tramo;
    }

    public int getBandera() {
        return bandera;
    }
    
}
