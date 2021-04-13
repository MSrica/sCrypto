package taapi_pckg;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Tmp {
	static String endpoint = "wma";
	static String currency = "BTC/USDT";
	static String interval = "1m";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder();
		
		pb.command("node", "index.js",endpoint,currency,interval);
	
		
		var proces = pb.inheritIO().start();
		
	
	}
}