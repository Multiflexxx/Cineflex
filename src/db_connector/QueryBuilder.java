package db_connector;

public class QueryBuilder {
    public static String createLoginQuery(String username, String passwordHash)
    {
        return "SELECT * FROM Person WHERE Vorname = '" + username + "' AND Passwort = '" + passwordHash + "' ;";
    }
}
