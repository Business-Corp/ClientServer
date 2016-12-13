package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import csa.server.mailserver.User;

public class TestUser {
	
	@Test
	public void testAuth(){
		String mail = "test@test.test";
		String pw = "passwd";
		
		User newUser = new User(mail, pw);
		
		assertTrue(newUser.auth(pw));
		
	}
	@Test
	public void testEquals(){
		String mail = "test@test.test";
		String pw = "passwd";
		
		User user1 = new User(mail,pw);
		User user2 = new User(mail,pw);
		
		assertTrue(user1.equals(user2));
		
	}

}
