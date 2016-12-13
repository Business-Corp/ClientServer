package csa.server.protokoll;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * H�lt sich eine synchronized HashMap, die alle SocketListener enth�lt. wenn ein neuer Listener angefordert wird, wird gepr�ft ob es diesen schon gibt. blah, 
 * keine ahnung..
 * @author H�ling
 *
 */
public class Sockets {
	
	private static HashMap<Integer, SocketListener> socketListener = new HashMap<Integer,SocketListener>();
	
	/**
	 * Liefert einen Socketlistener f�r den angegebnen Port.
	 * @param port ein Port auf dem der Listener h�ren soll muss 0 < port >= 65535 
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
