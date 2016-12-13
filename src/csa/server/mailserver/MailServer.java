package csa.server.mailserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import csa.server.protokoll.HoelingMail;
import csa.server.protokoll.SocketListener;
import csa.server.protokoll.Sockets;

public class MailServer extends Thread {

	//hat die mailbox
	Postamt mailbox = Postamt.getInstance();
	
	//hat einen persistenter
	
	//hat einen socket
	ServerSocket serversocket;
	
	private boolean listen = true;
	
	
	public MailServer() {
		mailbox.newUser("admin", "passwd");
	}
	
	
	@Override
	public void run() {

		
		try {
			
			
			serversocket = new ServerSocket(3377);
			
			Socket client;
			
			while(listen){
				
				ExecutorService threadpool = Executors.newWorkStealingPool();
				
				try {
					
					
					client = serversocket.accept();
					//threadpool.execute( neue verbindung );
					
					threadpool.execute(new HoelingMail(client));
					
					//hier den client dann an neuen thread aus threadpool übergeben. der neue Thread 
					//ist wieder vom Protokoll abhaengig.
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	public void interruptServer(){
		listen=false;
		try {
			serversocket.close();
		} catch (IOException e) {
			
//			e.printStackTrace();
		}
		
	}
	public int getMailCount(){
		return mailbox.mailCount();
	}



}
