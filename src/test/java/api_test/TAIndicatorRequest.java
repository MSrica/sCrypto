package api_test;

import java.io.IOException;
import java.util.ArrayList;


public class TAIndicatorRequest {

    public TAIndicatorRequest(ArrayList<String> endpoints, String currency, String interval) {

        ProcessBuilder pb = new ProcessBuilder();

        for (String endpoint : endpoints) {
            pb.command("node", "./src/test/resources/taapi_api/index.js",endpoint,currency,interval);
            Process proces;
            try {
                proces = pb.inheritIO().start();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            try {
                proces.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(proces.exitValue());
        }

    }
}
