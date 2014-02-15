package server.ircd.charybdis.config;

import server.ircd.ConfigBlock;
import server.ircd.IrcdConfig;
import server.ircd.IrcdConfigurationException;

public class ServerInfoSSL extends ConfigBlock {

	/**
	 * Our SSL private key.
	 */
	private String sslPrivateKey;
	
	/**
	 * Our SSL certificate.
	 */
	private String sslCert;
	
	/**
	 * Our SSL DH paramters. Generated with openssl dhparam -out dh.pem 1024.
	 */
	private String sslDhParams;
	
	/**
	 * The number of ssld processes desired. Should be 2 to (N-1) where N is
	 * the number of CPU cores available.
	 */
	private int ssldCount = 2;
	
	public ServerInfoSSL(IrcdConfig ircdConfig) {
		super(ircdConfig);
	}
	
	public void setSslPrivateKey(String sslPrivateKey) throws IrcdConfigurationException {
		if(!getIrcdConfig().getIrcd().getServer()
				.isFileReadableByIrcd(sslPrivateKey)) {
			throw new IrcdConfigurationException("SSL private key is not readable!");
		}
		this.sslPrivateKey = sslPrivateKey;
	}
	
	public void setSslCert(String sslCert) throws IrcdConfigurationException {
		if(!getIrcdConfig().getIrcd().getServer()
				.isFileReadableByIrcd(sslCert)) {
			throw new IrcdConfigurationException("SSL cert is not readable!");
		}
		this.sslCert = sslCert;
	}
	
	public void setDhParams(String sslDhParams) throws IrcdConfigurationException {
		if(!getIrcdConfig().getIrcd().getServer()
				.isFileReadableByIrcd(sslDhParams)) {
			throw new IrcdConfigurationException("SSL DH params is not readable!");
		}
		this.sslDhParams = sslDhParams;
	}

	public String getSslPrivateKey() {
		return sslPrivateKey;
	}

	public String getSslCert() {
		return sslCert;
	}

	public String getSslDhParams() {
		return sslDhParams;
	}

	public int getSsldCount() {
		return ssldCount;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(StringUtils.configLine("ssl_private_key",	sslPrivateKey));
		sb.append(StringUtils.configLine("ssl_cert", 		sslCert));
		sb.append(StringUtils.configLine("ssl_dh_params", 	sslDhParams));
		sb.append(StringUtils.configLine("ssld_count", 		ssldCount));
		return sb.toString();
	}
	
}
