package db;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login {
    protected static boolean getData(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        user.username = Setup.username();
        String tmpPassword = Setup.password();
        user.password = Encryption.getEncryptedBytes(tmpPassword, Constants.salt);

        return usernameAndPasswordCheck(user);
    }
    protected static boolean usernameAndPasswordCheck(User user){
        AtomicBoolean found = new AtomicBoolean(false);

        final String sqlSelectAllData = "SELECT * FROM " + Constants.tableName + " WHERE (USERNAME='" + user.username + "' AND PASS='" + Arrays.toString(user.password) + "')";
        try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllData);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()){
                user.email = rs.getString("EMAIL");
                user.apiKey = rs.getString("API_KEY");
                user.apiSecret = rs.getString("API_SECRET");
                user.taapiKey = rs.getString("TAAPI_KEY").getBytes();
                found.set(true);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return found.get();
    }
}