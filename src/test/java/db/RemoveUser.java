package db;

import java.sql.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RemoveUser {
    protected static boolean removeUser(User user){
        // returns true if user is successfully removed

        AtomicBoolean doubleCheck = new AtomicBoolean(Setup.doubleCheck());
        if(!doubleCheck.get()) return false;

        final String removeUser = "DELETE FROM " + Constants.tableName + " WHERE USERNAME='" + user.username + "' AND EMAIL='" + user.email + "'";
        try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(removeUser)){
            ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
}
