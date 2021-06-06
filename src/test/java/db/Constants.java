package db;

public class Constants {
    public static final String adminUsername = "thisintheadminusername";
    public static final String adminPassword = "bigAdminPasswordThatNobodyWillCrack6969";
    public static final String databaseName = "sCryptoUsers";
    public static final String serverName = "scryptousers.database.windows.net";
    public static final String databaseUrl = "jdbc:sqlserver://" + serverName + ":1433;database=" + databaseName + ";user=" + adminUsername + "@scryptousers;password=" + adminPassword + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    public static final String tableName = "aUser";
    public static final String saltString = "[-53, 51, 5, 102, 38, -119, 50, -115]";
    public static final byte[] salt = saltString.getBytes(); //PasswordEncryptionService.generateSalt();
}