package db;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AlterUser {
    protected static void alterUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        AtomicReference<String> alterUserSet = new AtomicReference<>("");
        AtomicReference<String> alterUserCheck = new AtomicReference<>("");
        AtomicReference<String> alterString = new AtomicReference<>("");
        AtomicInteger commandSetup = new AtomicInteger(Setup.whatToChange());

        if(doubleExisting(alterString, alterUserSet, alterUserCheck, commandSetup)) return;

        if(!updateDatabaseUser(user, alterUserSet)) return;

        updateCurrentUser(user, alterString, commandSetup);
    }
    protected static boolean doubleExisting(AtomicReference<String> alteredString, AtomicReference<String> alterUserSet, AtomicReference<String> alterUserCheck, AtomicInteger commandSetup) throws NoSuchAlgorithmException, InvalidKeySpecException {
        AtomicBoolean found = new AtomicBoolean(false);

        if(commandSetup.get() == 0) return !found.get();

        switch (commandSetup.get()) {
            case 1:
                alteredString.set(Setup.username());
                alterUserSet.set(" SET USERNAME='" + alteredString + "'");
                alterUserCheck.set("SELECT * FROM " + Constants.tableName + " WHERE USERNAME='" + alteredString + "'");
                break;
            case 2:
                alteredString.set(Setup.password());
                byte[] pass =  PasswordEncryptionService.getEncryptedPassword(alteredString.get(), Constants.salt);
                alterUserSet.set(" SET PASS='" + Arrays.toString(pass) + "'");
                break;
            case 3:
                alteredString.set(Setup.email());
                alterUserSet.set(" SET EMAIL='" + alteredString + "'");
                alterUserCheck.set("SELECT * FROM " + Constants.tableName + " WHERE EMAIL='" + alteredString + "'");
                break;
            case 4:
                alteredString.set(Setup.apiKey());
                alterUserSet.set(" SET API_KEY='" + alteredString + "'");
                alterUserCheck.set("SELECT * FROM " + Constants.tableName + " WHERE API_KEY='" + alteredString + "'");
                break;
            case 5:
                alteredString.set(Setup.apiSecret());
                alterUserSet.set(" SET API_SECRET='" + alteredString + "'");
                alterUserCheck.set("SELECT * FROM " + Constants.tableName + " WHERE API_SECRET='" + alteredString + "'");
                break;
        }

        // checking for existing data
        if(commandSetup.get() == 1 || commandSetup.get() == 3 || commandSetup.get() == 4 || commandSetup.get() == 5) {
            try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
                 PreparedStatement ps = conn.prepareStatement(alterUserCheck.get())) {
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                    found.set(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return found.get();
    }
    protected static boolean updateDatabaseUser(User user, AtomicReference<String> alterUserSet){
        AtomicReference<String> alterUser = new AtomicReference<>("");
        alterUser.set("UPDATE " + Constants.tableName + alterUserSet + " WHERE USERNAME='" + user.username + "' AND EMAIL='" + user.email + "' AND ID IS NOT NULL");

        try (Connection conn = DriverManager.getConnection(Constants.databaseUrl, Constants.adminUsername, Constants.adminPassword);
             PreparedStatement ps = conn.prepareStatement(alterUser.get())){
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    protected static void updateCurrentUser(User user, AtomicReference<String> alterString, AtomicInteger commandSetup) throws NoSuchAlgorithmException, InvalidKeySpecException {
        switch (commandSetup.get()) {
            case 1:
                user.username = alterString.get();
                break;
            case 2:
                user.password = PasswordEncryptionService.getEncryptedPassword(alterString.get(), Constants.salt);
                break;
            case 3:
                user.email = alterString.get();
                break;
            case 4:
                user.apiKey = alterString.get();
                break;
            case 5:
                user.apiSecret = alterString.get();
                break;
        }
    }
}