import java.io.Serializable;

// must implement Serializable in order to be sent
public class Message implements Serializable{
    private final String text;
    private final String archivo;

    public Message(String text, String archivo) {
        this.text = text;
        this.archivo = archivo;
    }

    public String getText() {
        return text;
    }

    public String getArchivo() {
        return archivo;
    }
}