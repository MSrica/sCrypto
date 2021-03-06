package tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class TAIndicatorReader {

    public Double macdValue;
    public Double macdSignal;
    public Double macdHist;
    public Double maValue;

    public TAIndicatorReader() {
        JSONParser parser = new JSONParser();

        try {
            // Parse "results.json" as JSON
            Object obj = parser.parse(new FileReader("./src/test/resources/taapi_api/results.json"));

            // Getting whole "results.json" as array -> getting one entry (second from behind)-> getting "result" object from "results.json"
            JSONArray jsonArray = (JSONArray) obj;
            JSONObject jsonObject = (JSONObject) jsonArray.get(jsonArray.size() - 2);
            JSONObject jsonResult = (JSONObject) jsonObject.get("result");

            // Check if second from behind object does not contain MA value
            if (jsonResult.get("value") == null){
                // Get MACD values
                Object macdValue = jsonResult.get("valueMACD");
                Object macdSignal = jsonResult.get("valueMACDSignal");
                Object macdHist = jsonResult.get("valueMACDHist");

                this.macdValue = (Double) macdValue;
                this.macdSignal = (Double) macdSignal;
                this.macdHist = (Double) macdHist;

                // Get last object and get its MA value
                jsonObject = (JSONObject) jsonArray.get(jsonArray.size() - 1);
                jsonResult = (JSONObject) jsonObject.get("result");

                Object maValue = jsonResult.get("value");

                this.maValue = (Double) maValue;
            } else {
                // Get MA value
                Object maValue = jsonResult.get("value");

                this.maValue = (Double) maValue;

                // Get last object and get its MACD values
                jsonObject = (JSONObject) jsonArray.get(jsonArray.size() - 1);
                jsonResult = (JSONObject) jsonObject.get("result");

                Object macdValue = jsonResult.get("valueMACD");
                Object macdSignal = jsonResult.get("valueMACDSignal");
                Object macdHist = jsonResult.get("valueMACDHist");

                this.macdValue = (Double) macdValue;
                this.macdSignal = (Double) macdSignal;
                this.macdHist = (Double) macdHist;
            }
            // Getting last entry-> getting "result" object from "results.json" to get MA200 value
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}