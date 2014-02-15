package server.ircd.charybdis.config;

public class StringUtils {

	public static String configLine(String key, Object value) {
		if(value instanceof String) {
			return "\t" + key + " = \"" + value + "\";\n";
		} else {
			return "\t" + key + " = " + value + ";\n";
		}
	}
	
}
