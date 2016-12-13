package application.server;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

import javax.xml.stream.events.StartElement;

import csa.server.mailserver.Mail;
import csa.server.mailserver.MailServer;
import csa.server.mailserver.User;

public class Moep {

	public static void main(String[] args) {
//		String test = "AUTH-user:name";
//		System.out.println(test.substring(5));
		
		String datumString = "11.07.2015 22:16";
//		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm",Locale.GERMAN);
//		LocalDate datum = LocalDate.parse(datumString,dtf);
//		System.out.println(datum);
////		
//		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN);
//		
//		Date datums;
//		try {
//			datums = format.parse(datumString);
//			System.out.println(datums);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		System.out.println(new Date().toString());
		Date datum = new Date();
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMAN);
		System.out.println(format.format(datum));
	
		Mail mail = new Mail("admin", "admin", "testmail", "testtext", new Date());
		
		MailServer ms = new MailServer();
		Thread client = new Thread(new csa.client.mailclient.SimpleMailTransfer(new User("admin","admin"),mail));
		
		System.out.println("starte server");
		ms.start();
		client.start();
		
		try {
			System.out.println("schlafe fuer 4 sekunden");
			Thread.currentThread().sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("interrupt server");
		ms.interruptServer();
		client.interrupt();
		
		try {
			System.out.println("warte auf join");
			ms.join();
			client.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ende im gelände");
		System.out.println("gehandelte mails: "+ms.getMailCount());

	}

}
