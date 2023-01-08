import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;

public class ServidorWeb {
	public static void main(String[] args) throws IOException {		
		try {
			byte[] array = Files.readAllBytes(Paths.get("vid1.mp4"));
			List<byte[]> clienteB = new ArrayList<>();;
			List<byte[]> clienteC = new ArrayList<>();;
			int i = 0;
			int j = 0;
			int k = 0;
			int banderasB[];
			int banderasC[];
			// System.out.println(new String(array));
			System.out.println("\n LONGITUD: \n" + array.length);	

			List<byte[]> chunks = getFileChunks(array);
			banderasB = new int[chunks.size()]; 
			banderasC = new int[chunks.size()]; 
			for(byte[] s:chunks){
				if((i%2) == 0){
					clienteB.add(s);
					banderasB[j] = i;	
					j ++;
				System.out.println("\n tramao" + s + " BanderaB " + banderasB[j-1]);
				}
				else{
					clienteC.add(s);
					banderasC[k] = i;
					k ++;
					System.out.println("\n tramao" + s + " BanderaC " + banderasC[k-1]);
				}
				i ++;
			}

			ServHilo client1 = new ServHilo("192.168.0.54", 8888, clienteB, banderasB);
			ServHilo client2 = new ServHilo("172.21.75.43", 8888, clienteC, banderasC);

			client1.start();
			client2.start();

			try {
				client1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				client2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public static List<byte[]> getFileChunks(byte[] mainFile) {
		int sizeMB = 1 * 1024 * 1024;
		List<byte[]> chunks = new ArrayList<>();
		for (int i = 0; i < mainFile.length; ) {
			byte[] chunk = new byte[Math.min(sizeMB, mainFile.length - i)];
			for (int j = 0; j < chunk.length; j++, i++) {
				chunk[j] = mainFile[i];
			}
			chunks.add(chunk);
		}
		return chunks;
	}
}


