import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;
 

public class CrunchifyNIOClient {
 
	public static void main(String[] args) throws IOException, InterruptedException {
 
		InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);
		SocketChannel crunchifyClient = SocketChannel.open(crunchifyAddr);
 
		log("Connecting to Server on port 1111...");
        String convString = "";
		for (;;) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("client: ");
            if (scanner.hasNext())
            {
                convString = scanner.nextLine();
                byte[] message = new String(convString).getBytes();
			    ByteBuffer buffer = ByteBuffer.wrap(message);
                crunchifyClient.write(buffer);
                buffer.clear();
            }
            ByteBuffer buff = ByteBuffer.allocate(77);
            crunchifyClient.read(buff);
            String result = new String(buff.array()).trim();
            log(result);
            if (result.equalsIgnoreCase("exit"))
            {
                break;
            }
			// wait for 2 seconds before sending next message
			//Thread.sleep(3000);
		}
		crunchifyClient.close();
	}
 
	private static void log(String str) {
		System.out.println(str);
	}
}