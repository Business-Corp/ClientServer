package csa.server.mailserver;

import java.util.Date;

public class Mail {
	
	private String absender;
	private String empfaenger;
	private String betreff;
	private String text;
	private Date sendeDatum;
	
	public Mail(String aabsender, String eempfaenger, String bbetreff, String ttext, Date aabsendeDatum) {
		
		absender=aabsender;
		empfaenger=eempfaenger;
		betreff=bbetreff;
		text=ttext;
		sendeDatum=aabsendeDatum;
		
	}

	public String getAbsender() {
		return absender;
	}

	public String getEmpfaenger() {
		return empfaenger;
	}

	public String getBetreff() {
		return betreff;
	}

	public String getText() {
		return text;
	}

	public Date getSendeDatum() {
		return sendeDatum;
	}

}
