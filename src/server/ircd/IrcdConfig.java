package server.ircd;

public class IrcdConfig {

	private Ircd ircd;
	
	public IrcdConfig(Ircd ircd) {
		this.ircd = ircd;
	}
	
	public Ircd getIrcd() {
		return ircd;
	}
	
}
