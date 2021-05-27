package db;

public class Constants {
    protected static final String adminUsername = "";
    protected static final String adminPassword = "";
    protected static final String databaseName = "sCryptoUsers";
    protected static final String serverName = "scryptousers.database.windows.net";
    protected static final String databaseUrl = "jdbc:sqlserver://" + serverName + ":1433;database=" + databaseName + ";user=" + adminUsername + "@scryptousers;password=" + adminPassword + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
    protected static final String tableName = "aUser";
    protected static final String saltString = "";
    protected static final byte[] salt = saltString.getBytes(); //Encryption.generateSalt();
}