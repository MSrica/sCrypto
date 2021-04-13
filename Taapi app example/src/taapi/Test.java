package taapi;


import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Test {
	static String endpoint = "rsi";
	static String currency = "BTC/USDT";
	static String interval = "1m";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder();
		
		pb.command("node", "Taapi/taapiConnector.js",endpoint,currency,interval);
	
		
		var proces = pb.inheritIO().start();
		
		
		
		
		
		
	}
	
	
	
	
	

}
