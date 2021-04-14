package taapi_pckg;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class Tmp {

	static String endpoint1 = "wma";
	static String endpoint2 = "sma";
	
	// Possible future addition of more TA-indicators
	// static String endpoint3 = "";
	// static String endpoint4 = "";
	
	static String currency = "BTC/USDT";
	static String interval = "1m";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder();
		
		
	
		pb.command("node", "index.js",endpoint1,currency,interval);
		var proces = pb.inheritIO().start();

		proces.waitFor();
		// System.out.println(proces.exitValue());
		
		pb.command("node", "index.js",endpoint2,currency,interval);
		proces = pb.inheritIO().start();

		proces.waitFor();
		// System.out.println(proces.exitValue());
	}
}