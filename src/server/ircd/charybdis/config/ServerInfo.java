package server.ircd.charybdis.config;

import java.net.*;

import com.google.common.net.InternetDomainName;

import server.ircd.IrcdConfigurationException;

public class ServerInfo {

	/**
	 * The name of the server.
	 */
	private String name;
	
	/**
	 * The unique server id of the server. It must be be 3 characters long.
	 * The first char must be digit, and the remaining 2 chars may be uppercase
	 * letters or digits.
	 */
	private String sid;
	
	/**
	 * The description of our server. '[' and ']' may not be used her for
	 * compatibility reasons.
	 */
	private String description;
	
	/**
	 * The name of the network this server is on. This is shown in the 005
	 * reply.
	 */
	private String networkName;

	/**
	 * The description of the network this server is on. This is shown in the
	 * 005 reply.
	 */
	private String networkDescription;
	
	/**
	 * True if this server should act as a hub, false otherwise.
	 */
	private boolean hub;
	
	/**
	 * The IPv4 address to bind to when we connect outward to IPv4 servers.
	 */
	private String vhost;
	
	/**
	 * The IPv6 address to bind to when we connect otward to IPv6 servers.
	 */
	private String vhost6;
	
	/**
	 * Our SSL configuration.
	 */
	private ServerInfoSSL sslConfig;
	
	/**
	 * The default maximum number of clients allowed to connect.
	 */
	private int defaultMaxClients = 1024;
	
	/**
	 * Enforced nick length.
	 */
	private int nicklen = 30;
	
	public void setName(String name) throws IrcdConfigurationException {
		if(!InternetDomainName.isValid(name)) {
			throw new IrcdConfigurationException("This IRC server's name needs to be styled like an internet domain name.");
		}
		this.name = name;
	}
	
	public void setSid(String sid) throws IrcdConfigurationException {
		if(sid.length() != 3) {
			throw new IrcdConfigurationException("The SID must be three characters long.");
		}
		if(!sid.matches("[0-9][A-Z0-9][A-Z0-9]")) {
			throw new IrcdConfigurationException("The SID is not in the correct format.");
		}
		this.sid = sid;
	}
	
	public void setDescription(String description) {
		this.description = description.replaceAll("\\[", "")	// Can't contain '[' for compatability reasons
				.replaceAll("\\]", "")							// Can't contain ']' for compatability reasons
				.replaceAll("\"", "\\\"");
	}
	
	public void setNetworkName(String networkName) {
		this.networkName = networkName.replaceAll("[^A-Za-z0-9]", "");
	}
	
	public void setNetworkDescription(String networkDescription) {
		this.networkDescription = networkDescription.replaceAll("\"", "\\\"");
	}
	
	public void setHub(boolean hub) {
		this.hub = hub;
	}
	
	public void setVhost(String vhost) throws IrcdConfigurationException {
		Inet4Address addy = null;
		try {
			addy = (Inet4Address) InetAddress.getByName(vhost);
		} catch (UnknownHostException e) {
			throw new IrcdConfigurationException("Vhost needs a valid IPv4 address.");
		}
		this.vhost = addy.getHostAddress();
	}
	
	public void setVhost6(String vhost6) throws IrcdConfigurationException {
		Inet6Address addy = null;
		try {
			addy = (Inet6Address) InetAddress.getByName(vhost6);
		} catch (UnknownHostException e) {
			throw new IrcdConfigurationException("Vhost6 needs a valid IPv6 address.");
		}
		this.vhost6 = addy.getHostAddress();
	}
	
	public void setDefaultMaxClients(int defaultMaxClients) throws IrcdConfigurationException {
		if(defaultMaxClients <= 0) {
			throw new IrcdConfigurationException("Default max clients must be a positive integer.");
		}
		this.defaultMaxClients = defaultMaxClients;
	}
	
	public void setNicklen(int nicklen) throws IrcdConfigurationException {
		if(nicklen <= 0) {
			throw new IrcdConfigurationException("Nicklen must be a positive integer.");
		}
		this.nicklen = nicklen;
	}

	public void setSslConfig(ServerInfoSSL sslConfig) {
		this.sslConfig = sslConfig;
	}
	
	public String getName() {
		return name;
	}

	public String getSid() {
		return sid;
	}

	public String getDescription() {
		return description;
	}

	public String getNetworkName() {
		return networkName;
	}

	public String getNetworkDescription() {
		return networkDescription;
	}

	public boolean isHub() {
		return hub;
	}

	public String getVhost() {
		return vhost;
	}

	public String getVhost6() {
		return vhost6;
	}

	public ServerInfoSSL getSslConfig() {
		return sslConfig;
	}

	public int getDefaultMaxClients() {
		return defaultMaxClients;
	}

	public int getNicklen() {
		return nicklen;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("serverinfo {\n");
		sb.append(StringUtils.configLine("name", name));
		sb.append(StringUtils.configLine("sid", sid));
		sb.append(StringUtils.configLine("description", description));
		sb.append(StringUtils.configLine("network_name", networkName));
		sb.append(StringUtils.configLine("network_desc", networkDescription));
		sb.append(StringUtils.configLine("hub", (hub ? "yes" : "no")));
		if(vhost != null)
			sb.append(StringUtils.configLine("vhost", vhost));
		if(vhost6 != null)
			sb.append(StringUtils.configLine("vhost6", vhost6));
		if(sslConfig != null)
			sb.append(sslConfig.toString());
		sb.append(StringUtils.configLine("default_max_clients", defaultMaxClients));
		sb.append(StringUtils.configLine("nicklen", nicklen));
		sb.append("};\n");
		return sb.toString();
	}

	
}
