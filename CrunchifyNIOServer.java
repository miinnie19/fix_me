import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

 
public class CrunchifyNIOServer {
	private static List<SocketModel> socketModel = new ArrayList<SocketModel>();
 
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
 
		// Selector: multiplexor of SelectableChannel objects
		Selector selector = Selector.open(); // selector is open here
 
		// ServerSocketChannel: selectable channel for stream-oriented listening sockets
		ServerSocketChannel crunchifySocket = ServerSocketChannel.open();
		InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);
 
		// Binds the channel's socket to a local address and configures the socket to listen for connections
		crunchifySocket.bind(crunchifyAddr);
 
		// Adjusts this channel's blocking mode.
		crunchifySocket.configureBlocking(false);
 
		int ops = crunchifySocket.validOps();
		SelectionKey selectKy = crunchifySocket.register(selector, ops, null);
 
		// Infinite loop..
        // Keep server running
        log("i'm a server and i'm waiting for new connection and buffer select...");
		while (true) {
			// Selects a set of keys whose corresponding channels are ready for I/O operations
			selector.select();
 
			// token representing the registration of a SelectableChannel with a Selector
			Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
			Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();
 
			while (crunchifyIterator.hasNext()) {
				SelectionKey myKey = crunchifyIterator.next();
 
				// Tests whether this key's channel is ready to accept a new socket connection
				if (myKey.isAcceptable()) {
					SocketChannel crunchifyClient = crunchifySocket.accept();
 
					// Adjusts this channel's blocking mode to false
					crunchifyClient.configureBlocking(false);
 
					String remoteAddress = crunchifyClient.getRemoteAddress().toString();
					// Operation-set bit for read operations
					//remoteAddress = remoteAddress.split(":")[1];
					socketModel.add(new SocketModel(crunchifyClient, remoteAddress.split(":")[1]));
					crunchifyClient.register(selector, SelectionKey.OP_READ);
					log("Connection Accepted: " + crunchifyClient.getLocalAddress() + " - " + remoteAddress + "\n");
 
					// Tests whether this key's channel is ready for reading
				} else if (myKey.isReadable()) {
					
					SocketChannel crunchifyClient = (SocketChannel) myKey.channel();
					ByteBuffer crunchifyBuffer = ByteBuffer.allocate(256);
                    crunchifyClient.read(crunchifyBuffer);
                    String result = new String(crunchifyBuffer.array()).trim();
                    // log("Message received ["+ crunchifyClient.getRemoteAddress() +"]: " + result);
                    log(crunchifyClient.getRemoteAddress() + " : " + result);
                    
                    //Thread.sleep(2000);
                    if (result.equalsIgnoreCase("exit"))
                    {
                        byte[] str = ("EXIT").getBytes();
                        ByteBuffer str2 = ByteBuffer.wrap(str);
                        crunchifyClient.write(str2);
						crunchifyClient.close();
						//socketModel.remove(crunchifyClient);
                    }
                    else 
                    {
						byte[] str = ("You sent: " + result).getBytes();
                        ByteBuffer str2 = ByteBuffer.wrap(str);
						crunchifyClient.write(str2);
						// Random random = new Random();
						// SocketModel sm = socketModel.get(0);
					
						// byte[] str3 = ("Hello....").getBytes();
						// ByteBuffer str4 = ByteBuffer.wrap(str3);
						// sm.getSocketChannel().write(str4);
					}
				}

				

				crunchifyIterator.remove();
			}

			for (SocketModel sm: socketModel) {
				System.out.println(sm.getId() + ". " + sm.getRemoteAddress());
			}
        }
        
	}
 
	private static void log(String str) {
		System.out.println(str);
	}
}
