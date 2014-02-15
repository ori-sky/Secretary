package server.ircd;

import server.Server;

public class Ircd {

	private Server server;
	
	public Ircd(Server server) {
		this.server = server;
	}
	
	public Server getServer() {
		return server;
	}
	
}
