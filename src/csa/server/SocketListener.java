package csa.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class SocketListener implements Runnable{
	
	private int port;
	private String name;
	
	private boolean listen = true;
	private ServerSocket server;
	
	public SocketListener(String name, int port) {

		this.name=name;
		this.port=port;
		
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log("Socket auf Port "+port+" konnte nicht geoeffnet werden");
		}
		
	}

	@Override
	public void run() {
		
		Socket client;
		
		while(listen){
			
			try {
				
				
				client = server.accept();
				
				//hier den client dann an neuen thread aus threadpool übergeben. der neue Thread 
				//ist wieder vom Protokoll abhaengig.
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	private void log(String message){
//		Logger.getLogger("ServerLogger").getResourceBundle().
		
	}

}
