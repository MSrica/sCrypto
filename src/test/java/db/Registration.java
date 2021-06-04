package db;

import org.apache.commons.lang3.StringUtils;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Registration {
    public static boolean addData(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // returns true if entire adding process is successful

//        enterUserData(user);
        if(chekExisting(user)) return false;
        return addUserData(user);
    }
    protected static void enterUserData(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        user.username = Setup.username();
        System.out.println("Password needs to have more than 8 characters, at least one uppercase letter and only alphanumeric characters");
        String tmpPassword = Setup.password();
        user.password = Encryption.getEncryptedBytes(tmpPassword, Constants.salt);
        user.email = Setup.email();
        user.apiKey = Setup.apiKey();
        user.apiSecret = Setup.apiSecret();
        String tmpTaapiKey = Setup.taapiKey();
        user.taapiKey = Encryption.getEncryptedBytes(tmpTaapiKey, Constants.salt);
        if (!(tmpPassword.length() >= 8 && !StringUtils.isAllLowerCase(tmpPassword) && tmpPassword.matches(".*\\d.*"))) enterUserData(user);
    }
    protected static boolean chekExisting(User user){
        // returns true if user data exists

        AtomicBoolean doubleUser = new AtomicBoolean(false);
        final String sqlSelectExistingData = "SELECT * FROM " + Constants.tableName + " WHERE USERNAME='" + user.username + "' OR EMAIL='" + user.email + "' OR API_KEY='" + user.apiKey + "' OR API_SECRET='" + user.apiSecret + "' OR TAAPI_KEY='" + Arrays.toString(user.taapiKey) + "'";
        try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(sqlSelectExistingData);
             ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                String name = rs.getString("USERNAME");
                String email = rs.getString("EMAIL");
                String apiKey = rs.getString("API_KEY");
                String apiSecret = rs.getString("API_SECRET");
                String taapiKey = rs.getString("TAAPI_KEY");

                if(name.equals(user.username)) System.out.println("Username already in use!");
                if(email.equals(user.email)) System.out.println("Email already in use!");
                if(apiKey.equals(user.apiKey)) System.out.println("API key already in use!");
                if(apiSecret.equals(user.apiSecret)) System.out.println("API secret already in use!");
                if(taapiKey.equals(Arrays.toString((user.taapiKey)))) System.out.println("TAAPI key already in use!");

                doubleUser.set(true);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        if(!doubleUser.get()) System.out.println("No existing data found!");
        return doubleUser.get();
    }
    protected static boolean addUserData(User user){
        // returns true if adding is successful

        final String addUser = "INSERT INTO " + Constants.tableName + "(USERNAME, PASS, EMAIL, API_KEY, API_SECRET, TAAPI_KEY)\n" + "VALUES ('" + user.username + "', '" + Arrays.toString(user.password) + "', '" + user.email + "', '" + user.apiKey + "', '" + user.apiSecret  + "', '" + Arrays.toString(user.taapiKey) + "')";
        try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
            PreparedStatement ps = conn.prepareStatement(addUser)){
            ps.executeUpdate();
            System.out.println("User data added!");
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("User data failed to add!");
        return false;
    }
}