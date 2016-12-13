package csa.server.mailserver;

public class User {
	
	private String email;
	private String passwd;
	private String vorname="";
	private String nachname="";
	
	public User(String emai, String password) {
		
		email=emai;
		passwd=password;
		
	}
	public boolean auth(String password){
		
		return password.equals(passwd);
	}
	@Override
	public boolean equals(Object obj) {

		if(this.getClass().equals(obj.getClass())){
			User temp = (User) obj;
			
			if(email.equals(temp.email)){
				return true;
			}
			
			return false;
			
			
		}
		
		return false;
	}
	public String getEmail(){
		return email;
	}
	

}
