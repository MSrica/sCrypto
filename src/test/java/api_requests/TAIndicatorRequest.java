package api_requests;

import java.io.IOException;

public class TAIndicatorRequest {

    public TAIndicatorRequest(String tradingPair, String interval) {

        ProcessBuilder pb = new ProcessBuilder();

        pb.command("node", "./src/test/resources/taapi_api/index.js",tradingPair, interval);
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
//        System.out.println(proces.exitValue());
    }
}
