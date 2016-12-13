package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import csa.server.protokoll.SocketListener;
import csa.server.protokoll.Sockets;

public class TestSockets{

	@Test
	public void testgetSockets(){
		
		SocketListener sl1 = Sockets.getServerSocket(3377);
		SocketListener sl2= Sockets.getServerSocket(3377);
		
		assertNotNull(sl1);
		assertNull(sl2);
		
	}

}
