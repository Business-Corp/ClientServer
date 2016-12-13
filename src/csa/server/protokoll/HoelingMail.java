package csa.server.protokoll;

import java.net.Socket;

public class HoelingMail implements Runnable{

	private Socket client;
	
	public HoelingMail(Socket socket) {


		client=socket;
	}

	@Override
	public void run() {
		
		
		//authentizieren
		
		//kommunizieren - was will der client?
		
		//kommt noch mehr?
		 //beenden
		
	}
	
}
