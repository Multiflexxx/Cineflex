package db_connector;

import java.util.*;
import java.text.*;

public class QueryBuilder {
    public static String createLoginQuery(String email, String passwordHash)
    {
        return "SELECT * FROM Person WHERE `E-Mail` = '" + email + "' AND `Passwort` = '" + passwordHash + "' ;";
    }

    public static String createUser(String name, String lastname, String gebDate, String email, String passwordHash)
    {
        return "INSERT INTO Person (`Vorname`, `Nachname`, `GebDatum`, `E-Mail`, `Passwort`) VALUES ('"+name+"', '"+lastname+"', '"+gebDate+"', '"+email+"', '"+passwordHash+"'); \n INSERT INTO Kunde (`PID`, `Treuepunkte`) VALUES ((SELECT `PID` FROM Person WHERE `Vorname` = '"+name+"' AND `Nachname` = '"+lastname+"' AND `GebDatum` = '"+gebDate+"' AND `E-Mail` = '"+email+"' AND `Passwort` = '"+passwordHash+"'), 0);";
    }

    public static String showCinemaImaginationsToday()
    {
        return "SELECT * FROM Vorstellung WHERE `Datum` = '"+ getDateAsString() +"'; ";
    }

    public static String showCinemaImaginationsThisWeek()
    {
        return "SELECT * FROM Vorstellung WHERE `Datum` >= '"+ getDateAsString() +"'; ";
    }

    private static String getDateAsString()
    {
        DateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        return formatter.format(new Date());
    }
}
