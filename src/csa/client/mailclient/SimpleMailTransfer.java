package csa.client.mailclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import csa.server.mailserver.Mail;
import csa.server.mailserver.User;

public class SimpleMailTransfer implements Runnable {

	private User user;
	private Mail email;
	private Socket anServer=null;
	private Scanner in;
	private PrintWriter out;
	
	
	public SimpleMailTransfer(User authUser, Mail mail) {
		user=authUser;
		email=mail;
	}
	
	@Override
	public void run() {
		
		try {
			long startMillis = System.currentTimeMillis();
			
			anServer = new Socket("localhost", 3377);
			in = new Scanner(anServer.getInputStream());
			out = new PrintWriter(anServer.getOutputStream(), true);
			
			String server="";
			
			out.println("AUTH-admin:passwd");
			
			server=in.nextLine();
			
			
			if(server.equals("OK")){
				//ab hier mail übertragen
				Date datum = new Date();
				DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);
				String trennzeichen ="<>";
				
				String mail =""+email.getAbsender()+trennzeichen;
				mail=mail+email.getEmpfaenger()+trennzeichen;
				mail=mail+format.format(datum)+trennzeichen;
				mail=mail+email.getBetreff()+trennzeichen;
				mail=mail+email.getText();
				
				
				out.println("1100-"+mail);
				
				server=in.nextLine();
				
				
				
				if(server.equals("OK")){
					out.println(".");
				}else{
					if(server.equals("1110")){
						
					}
				}
				in.nextLine();
				
				long endMillis=System.currentTimeMillis();
				
				Long dauer= endMillis-startMillis;
				
				System.out.println("client verbindung und abholen von daten in millis: "+dauer);
				
				anServer.close();
				
			}
	
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			run();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
