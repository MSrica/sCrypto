package GUI.preferences;
import java.io.*;
import java.util.Properties;

public class LocalProperties {
    private Properties prop;

    public LocalProperties() {
        this.prop = new Properties();
    }

    public void setPropertyString(String key, String value) {
        try (OutputStream output = new FileOutputStream("src/test/resources/config.properties")) {
            //trying to save previous properties
            String username;
            String password;
            String tradingPair;
            String interval;
            //try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            //    // load a properties file
            //    prop.load(input);
            //    username = prop.getProperty("username", null);
            //    password = prop.getProperty("password", null);
            //    tradingPair = prop.getProperty("tradingPair", null);
            //    interval = prop.getProperty("interval", null);
//
            //    System.out.println("backUp: " + username + " " + password + " " + tradingPair + " " + interval);
            //} catch (IOException io) {
            //    io.printStackTrace();
            //    System.out.println("Couldn't save last properties :( Aborting setting new ones");
            //    return;
            //}
            // Properties prop = new Properties();
            // set the properties value
            prop.setProperty(key, value);
            // save properties to project root folder
            prop.store(output, null);
//            System.out.println("props on end of setProp: " + prop);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    public String getPropertyString(String key) {
        try (InputStream input = new FileInputStream("src/test/resources/config.properties")) {
            // Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // set the properties value
            prop.getProperty(key, "default");
//            System.out.println("Property "+ prop.getProperty(key, "default"));

            return prop.getProperty(key, "default");
        } catch (IOException io) {
            io.printStackTrace();
            return "Error: " + io.toString();
        }
    }
}