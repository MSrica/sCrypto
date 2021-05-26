package db;

import org.apache.commons.lang3.StringUtils;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Registration {
    protected static boolean addData(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        enterUserData(user);
        if(chekExisting(user)) return false;
        return addUserData(user);
    }
    protected static void enterUserData(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        user.username = Setup.username();
        System.out.println("Password needs to have more than 8 characters, at least one uppercase letter and only alphanumeric characters");
        String tmpPassword = Setup.password();
        user.password = PasswordEncryptionService.getEncryptedPassword(tmpPassword, Constants.salt);
        user.email = Setup.email();
        user.apiKey = Setup.apiKey();
        user.apiSecret = Setup.apiSecret();
        if (!(tmpPassword.length() >= 8 && !StringUtils.isAllLowerCase(tmpPassword) && tmpPassword.matches(".*\\d.*")))
              enterUserData(user);
    }
    protected static boolean chekExisting(User user){
        AtomicBoolean doubleUser = new AtomicBoolean(false);
        final String sqlSelectExistingData = "SELECT * FROM " + Constants.tableName + " WHERE USERNAME='" + user.username + "' OR EMAIL='" + user.email + "' OR API_KEY='" + user.apiKey + "' OR API_SECRET='" + user.apiSecret + "'";
        try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(sqlSelectExistingData);
             ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                String name = rs.getString("USERNAME");
                String email = rs.getString("EMAIL");
                String apiKey = rs.getString("API_KEY");
                String apiSecret = rs.getString("API_SECRET");

                if(name.equals(user.username)) System.out.println("Username already in use!");
                if(email.equals(user.email)) System.out.println("Email already in use!");
                if(apiKey.equals(user.apiKey)) System.out.println("API key already in use!");
                if(apiSecret.equals(user.apiSecret)) System.out.println("API secret already in use!");

                doubleUser.set(true);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return doubleUser.get();
    }
    protected static boolean addUserData(User user){
        // adding user query
        final String addUser = "INSERT INTO " + Constants.tableName + "(USERNAME, PASS, EMAIL, API_KEY, API_SECRET)\n" +
                "VALUES ('" + user.username + "', '" + Arrays.toString(user.password) + "', '" +  user.email + "', '" + user.apiKey + "', '" + user.apiSecret  + "')";
        // adding user
        try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(addUser)){
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}