package db_connector;

public class QueryBuilder {
    public static String createLoginQuery(String username, String passwordHash)
    {
        return "SELECT * FROM Person WHERE `Vorname` = '" + username + "' AND `Passwort` = '" + passwordHash + "' ;";
    }

    public static String createUser(String name, String lastname, String gebDate, String email, String passwordHash)
    {
        return "INSERT INTO Person (`Vorname`, `Nachname`, `GebDatum`, `E-Mail`, `Passwort`) VALUES ('"+name+"', '"+lastname+"', '"+gebDate+"', '"+email+"', '"+passwordHash+"'); INSERT INTO Kunde (`PID`, `Treuepunkte`) VALUES (SELECT `PID` FROM Person WHERE `Vorname` = '"+name+"' AND `Nachname` = '"+lastname+"' AND `GebDatum` = '"+gebDate+"' AND `E-Mail` = '"+email+"' AND `Passwort` = '"+passwordHash+"', 0)";
    }
}
