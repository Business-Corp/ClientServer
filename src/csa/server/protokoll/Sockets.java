package csa.server.protokoll;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Hält sich eine synchronized HashMap, die alle SocketListener enthält. wenn ein neuer Listener angefordert wird, wird geprüft ob es diesen schon gibt. blah, 
 * keine ahnung..
 * @author Höling
 *
 */
public class Sockets {
	
	private static HashMap<Integer, SocketListener> socketListener = new HashMap<Integer,SocketListener>();
	
	/**
	 * Liefert einen Socketlistener für den angegebnen Port.
	 * @param port ein Port auf dem der Listener hören soll muss 0 < port >= 65535 
	 * 
	 * @return - {@code null} wenn es bereits einen Listener gibt oder {@code port < 1 || port > 65535}.
	 * <br>Ansonsten einen neuen Listener 
	 */
	public static synchronized SocketListener getServerSocket(int port){
		
		if(!(port > 0 && port <= 65535)){
			return null;
		}
		
		if(socketListener.containsKey(port)){
			return null;
		}else{
			SocketListener sl = new SocketListener(port);
			
			socketListener.put(port, sl);
			return sl;
			
		}
				
		
	}

}
