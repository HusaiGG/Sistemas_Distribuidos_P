
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

            // String P1 = Base64.getEncoder().encodeToString(chunks.get(0));
            // System.out.println(P1);

            HashMap<Integer, byte[]> hmap_par = new HashMap<Integer, byte[]>();
            HashMap<Integer, byte[]> hmap_impar = new HashMap<Integer, byte[]>();

            System.out.println("creando hashmap");
            for (int i = 0; i < chunks.size(); i += 2) {
                hmap_par.put(i, chunks.get(i));
                hmap_impar.put(i + 1, chunks.get(i + 1));
            }

            System.out.println("HASHMAP CREADO");
            FileOutputStream fos = new FileOutputStream("hashmap_par.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(hmap_par);
            oos.close();
            fos.close();
            System.out.println("Serialized HashMapPar data is saved in hashmap_impar.ser");
            System.out.println("Size HashMapPar: " + hmap_par.size());

            System.out.println("HASHMAP CREADO");
            FileOutputStream fos2 = new FileOutputStream("hashmap_impar.ser");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(hmap_impar);
            oos2.close();
            fos2.close();
            System.out.println("Serialized HashMapImpar data is saved in hashmap_par.ser");
            System.out.println("Size HashMapImar: " + hmap_impar.size());

            // System.out.println("\n\nHashMap IMPAR");
            // for (Integer key : hmap_impar.keySet()) {
            // System.out.println(Base64.getEncoder().encodeToString(hmap_impar.get(key)));

            // }
            // System.out.println("\n\nHashMap PAR");
            // for (Integer key : hmap_par.keySet()) {
            // System.out.println(hmap_par.get(key));
            // }

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
