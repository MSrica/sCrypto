package GUI.preferences;
import java.io.*;
import java.util.Properties;

public class LocalProperties {
    //TODO: try getting existing values to a String var, append the new key-value pairs, store it back into config.prop
    private Properties prop;

    public LocalProperties() {
        this.prop = new Properties();
    }

    public void setPropertyString(String key, String value) {
        try (OutputStream output = new FileOutputStream("src/test/resources/config.properties")) {
            // Properties prop = new Properties();
            // set the properties value
            prop.setProperty(key, value);
            // save properties to project root folder
            prop.store(output, null);
            System.out.println(prop);
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
            System.out.println("Property "+ prop.getProperty(key, "default"));

            return prop.getProperty(key, "default");
        } catch (IOException io) {
            io.printStackTrace();
            return "Error: " + io.toString();
        }
    }
}