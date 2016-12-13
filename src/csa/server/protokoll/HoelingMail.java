package csa.server.protokoll;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HoelingMail implements Runnable{

	private Socket client;
	private Scanner in = null;
	private PrintWriter out = null;
	
	public HoelingMail(Socket socket) {


		client=socket;
	}

	@Override
	public void run() {
		
		
		//authentizieren
		
		//kommunizieren - was will der client?
		
		//kommt noch mehr?
		 //beenden
		try {
			
			
			
			in=new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream(), true);
			
			if (authenticate()) {
				// wenn erfolgreich, kann festgestellt werden was getan werden
				// soll

				communicate();

			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private boolean authenticate() {
		
		out.println("AUTH");
		
		
		return false;
	}
	
}
