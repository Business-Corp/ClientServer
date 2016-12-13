package csa.server.mailserver;

import java.util.ArrayList;
import java.util.LinkedList;

public class Postfach {

	private LinkedList<Mail> mail = new LinkedList<Mail>();
	
	private int letzeSync =0;
	
	private User gehoert;
	
	public Postfach(User benutzer) {
	
		gehoert=benutzer;
		
	}
	
	
	public void addMail(Mail neueMail){
		
		if(neueMail!=null){
			mail.add(neueMail);
		}
		
	}
	public Mail getLastMail(){
		if(mail.size()>0){
			return mail.getLast();
		}
		return null;
	}
	
}
