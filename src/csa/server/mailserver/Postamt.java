package csa.server.mailserver;

import java.util.HashMap;

public class Postamt {
	
	//--------------------Singelton-------------------------------------------------
	private static Postamt postamt = new Postamt();
	
	private Postamt() {
		
	}
	
	public static Postamt getInstance() {
		return postamt;
	}
	//------------------------------------------------------------------------------

	
	private int handledMails =0;
	
	private HashMap<String,User> userMap = new HashMap<String,User>();
	private HashMap<User,Postfach> postfachMap = new HashMap<User,Postfach>();
	
	
	public void newUser(String email, String passwd){
		
		
		if(!userMap.containsKey(email)){
			User user = new User(email, passwd);
			Postfach pf = new Postfach(user);
			
			userMap.put(email, user);
			postfachMap.put(user, pf);
			
		}
	}
	public boolean authUser(String email, String passwd){
		
		if(userMap.containsKey(email)){
			User user = userMap.get(email);
			
			return user.auth(passwd);
			
		}
		
		return false;
	}
	public void addMail(String emailAdresse, Mail email){
		
		if(userMap.containsKey(emailAdresse)){
			User user = userMap.get(emailAdresse);
			Postfach upf = postfachMap.get(user);
			
			upf.addMail(email);
			
		}
		
	}
	/**
	 * 
	 * @param email
	 * @return null wenn es keine mails gibt oder es die email nicht gibt
	 */
	public Mail getLastMail(String email){
		
		if(userMap.containsKey(email)){
			User user = userMap.get(email);
			Postfach upf = postfachMap.get(user);
			
			return upf.getLastMail();
			
		}
		
		return null;
	}
	
	
}
