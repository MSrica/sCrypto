package db;

import java.sql.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login {
    protected static boolean getData(User user){
        user.username = Setup.username();
        user.password = Setup.password();

        return usernameAndPasswordCheck(user);
    }
    protected static boolean usernameAndPasswordCheck(User user){
        // variables
        AtomicBoolean found = new AtomicBoolean(false);

        // finding user
        final String sqlSelectAllData = "SELECT * FROM " + Constants.tableName + " WHERE (USERNAME='" + user.username + "' AND PASS='" + user.password + "')";
        try (Connection conn = DriverManager.getConnection(Setup.connectionUrl(), Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllData);
             ResultSet rs = ps.executeQuery()) {
            // if a user is found setting the variable to true
            while (rs.next()){
                user.email = rs.getString("EMAIL");
                user.apiKey = rs.getString("API_KEY");
                user.apiSecret = rs.getString("API_SECRET");
                found.set(true);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return found.get();
    }
}