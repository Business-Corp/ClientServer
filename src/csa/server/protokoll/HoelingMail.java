package csa.server.protokoll;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import csa.server.mailserver.Mail;
import csa.server.mailserver.Postamt;

public class HoelingMail implements Runnable {

	private Socket client;
	private Scanner in = null;
	private PrintWriter out = null;
	private boolean authenticated = false;
	private Postamt postamt = Postamt.getInstance();
	private String usermail;
	private String trennzeichen = "<>";
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN);

	public HoelingMail(Socket socket) {

		client = socket;
	}

	@Override
	public void run() {

		// authentizieren

		// kommunizieren - was will der client?

		// kommt noch mehr?
		// beenden
		try {

			in = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream(), true);

			String client = "";

			while (!client.equals(".")) {

				client=communicate(in.nextLine());
				out.println(client);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String communicate(String nextLine) {
		
		
		//der user soll authentisiert werden
		if(nextLine.startsWith("AUTH-")){
			return auth(nextLine.substring(5));
		}
		if(authenticated){
		
			if(nextLine.startsWith("1100-")){
				return mailToServer(nextLine.substring(5));
			}else if(nextLine.startsWith("2100-")){
				return mailToClient();
			}
			//ab hier kann der rest passieren
			
		}
		
		return ".";
		

	}

	private String mailToClient() {
		// Hier wird die letzte mail an den client übertragen.
		
		out.println("OK");
		Mail mail = postamt.getLastMail(usermail);
		
		String email = ""+mail.getAbsender()+trennzeichen;
		email += mail.getEmpfaenger()+trennzeichen;
		email += dateFormat.format(mail.getSendeDatum())+trennzeichen;
		email += mail.getBetreff()+trennzeichen;
		email += mail.getText();
		
		out.println(email);
		
		return in.nextLine();
		
		
		
	}

	private String mailToServer(String client) {
		//das ggf auch von client übergeben lassen

		
//		System.out.println(client);
		
		String[] mail = client.split(trennzeichen);

		
		if(mail.length<5){
			return "1120";
		}
		
		
		String datumString = mail[2];

		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMAN);
		Date datums;

		//datum wird noch nicht richtig geparst
		
		try {
			datums = format.parse(datumString);
//			datums = new Date(datumString);

		} catch (ParseException e) {

			
//			datums=new Date();
			return "1110";
//			e.printStackTrace();
		}
		
		
		Mail userMail = new Mail(mail[0], mail[1], mail[3], mail[4], datums);
		postamt.addMail(usermail, userMail);
		
		return "OK";
	}

	/**
	 * authentisiert den übergebnen user mit seinem PW
	 * @param client
	 * @return
	 */
	private String auth(String client) {

		String[] temp = client.split(":");
		
		if(postamt.authUser(temp[0], temp[1])){
			authenticated=true;
			usermail=temp[0];
			return "OK";
		}
		return "NOK";
		
	}



}
