package db;

import java.sql.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class RemoveUser {
    protected static boolean removeUser(User user){
        // double checking
        AtomicBoolean doubleCheck = new AtomicBoolean(Setup.doubleCheck());
        if(!doubleCheck.get()) return false;

        // deleting user query
        final String removeUser = "DELETE FROM " + Constants.databaseName + "." + Constants.tableName + " WHERE USERNAME='" + user.username + "' AND EMAIL='" + user.email + "'";
        // deleting user
        try (Connection conn = DriverManager.getConnection(Setup.connectionUrl(), Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(removeUser)){
            int rs = ps.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }
}
