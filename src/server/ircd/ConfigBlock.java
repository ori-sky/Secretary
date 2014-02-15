package server.ircd;

public class ConfigBlock {

	private IrcdConfig ircdConfig;
	
	public ConfigBlock(IrcdConfig ircdConfig) {
		this.ircdConfig = ircdConfig;
	}
	
	public IrcdConfig getIrcdConfig() {
		return ircdConfig;
	}
	
}
