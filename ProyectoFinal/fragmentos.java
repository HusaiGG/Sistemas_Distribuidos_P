
//package Hasmap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.io.FileOutputStream;

public class fragmentos {
    public static void main(String[] args) throws IOException {
        System.out.println("Hola Mundo");
        try {
            byte[] array = Files.readAllBytes(Paths.get("video.mp4"));

            // System.out.println(new String(array));
            System.out.println("\n LONGITUD: \n" + array.length);

            List<byte[]> chunks = getFileChunks(array);

            // System.out.println(Base64.getEncoder().encodeToString(chunks.get(0)));

            String P1 = Base64.getEncoder().encodeToString(chunks.get(0));

            HashMap<Integer, String> hmap_par = new HashMap<Integer, String>();
            HashMap<Integer, String> hmap_impar = new HashMap<Integer, String>();

            System.out.println("creando hashmap");
            for (int i = 0; i < chunks.size(); i += 2) {
                hmap_par.put(i, Base64.getEncoder().encodeToString(chunks.get(i)));
                hmap_par.put(i + 1, Base64.getEncoder().encodeToString(chunks.get(i + 1)));
            }

            System.out.println("HASHMAP CREADO");
            FileOutputStream fos = new FileOutputStream("hashmap_par.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hmap_par);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMapPar data is saved in hashmap.ser");

            System.out.println("HASHMAP CREADO");
            FileOutputStream fos2 = new FileOutputStream("hashmap_impar.ser");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(hmap_par);
            oos2.close();
            fos2.close();
            System.out.printf("Serialized HashMapImpar data is saved in hashmap.ser");

            int size = chunks.size();
            System.out.println("Size of list: " + size);

            FileOutputStream fos3 = new FileOutputStream("vid2.mp4");
            fos3.write(array);
            fos3.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static List<byte[]> getFileChunks(byte[] mainFile) {
        int sizeMB = 1 * 1024 * 1024;
        List<byte[]> chunks = new ArrayList<>();
        for (int i = 0; i < mainFile.length;) {
            byte[] chunk = new byte[Math.min(sizeMB, mainFile.length - i)];
            for (int j = 0; j < chunk.length; j++, i++) {
                chunk[j] = mainFile[i];
            }
            chunks.add(chunk);
        }
        return chunks;
    }
}
