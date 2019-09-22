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

    public static String showAllCinemas()
    {
        return "SELECT Ort.Ortsname, Ort.PLZ FROM Ort INNER JOIN Gebäude ON Ort.PLZ=Gebäude.PLZ ORDER BY `Ortsname`;";
    }

    public static String showCinemaImaginationsToday()
    {
        return "SELECT * FROM Vorstellung WHERE `Datum` = '" + getDateAsString() + "' AND `Uhrzeit` >= '"+getTimeAsString()+"';";
    }

    public static String showCinemaImaginationsThisWeek()
    {
        return "SELECT * FROM Vorstellung WHERE `Datum` >= '" + getDateAsString() + "' AND `Uhrzeit` >= '" + getTimeAsString() + "';";
    }

    public static String showTitlePageFilms()
    {
        return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE `Datum` >= '" + getDateAsString() +"' LIMIT 3;";
    }

    public static String showAllFilmInfos(String filmTitel)
    {
        return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE `Titel` = '" + filmTitel + "'";
    }

    public static String showGenresForFilmTitle(String filmTitel)
    {
        return "SELECT `Genrebezeichnung`, `Deskriptor` FROM Genre, Film, FilmGenre WHERE FilmGenre.GenreID = Genre.GenreID AND Film.FilmID = FilmGenre.FilmID AND `Titel` = '" + filmTitel + "';";
    }

    public static String showGenresForFilmID(int filmID)
    {
        return "SELECT `Genrebezeichnung`, `Deskriptor` FROM Genre, Film, FilmGenre WHERE FilmGenre.GenreID = Genre.GenreID AND Film.FilmID = FilmGenre.FilmID AND Film.FilmID = " + filmID + ";";
    }

    public static String showSearchResults(String search, String date, String time, int fsk)
    {
        if(search != "") {
            return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "WHERE (`Titel` LIKE '%" + search + "%' OR `Beschreibung` LIKE '%" + search + "%') " +
                    "AND `Datum` >= '" + date + "'" +
                    "AND `Uhrzeit` >= '" + time + "'" +
                    "AND `FSK` <= " + fsk + " ;";
        }else {
            return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "WHERE `Datum` >= '" + date + "'" +
                    "AND `Uhrzeit` >= '" + time +
                    "AND `FSK` <= " + fsk + " ;";
        }
    }

    public static String defaultSearchQuery(String search, String date, String time, int fsk) {
        if(search != "") {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "WHERE (`Titel` LIKE '%" + search + "%' OR `Beschreibung` LIKE '%" + search + "%') " +
                    "AND `Datum` >= '" + date + "' " +
                    "AND `Uhrzeit`>= '" + time + "' "+
                    "AND `FSK` <= " + fsk + " ;";
        } else {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "WHERE `Datum` >= '" + date + "' " +
                    "AND `Uhrzeit`>= '" + time + "' "+
                    "AND `FSK` <= " + fsk + " ;";
        }

    }

    public static String showMovieById(String id) {
//        return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` " +
//                "FROM Vorstellung Join Film ON Film.FilmID = Vorstellung.FilmID Join Sprache ON Vorstellung.SprachID = Sprache.SprachID Join Saal ON Saal.VorstellungsID = Vorstellung.VorstellungsID Join Sitzplan ON Sitzplan.SaalID = Saal.SaalID " +
//                "Where Vorstellung.FilmID = " + id + " ;";

        return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` " +
                    "FROM Vorstellung " +
                        "Join Film ON Vorstellung.FilmID = Film.FilmID " +
                        "JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID " +
                        "JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID " +
                        "JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID " +
                        "JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID " +
                    "WHERE Film.ID = " + id + " " +
                        "JOIN";

//        SELECT DISTINCT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname`
//        FROM Vorstellung
//        JOIN Film ON Vorstellung.FilmID = Film.FilmID
//        JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID
//        JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID
//        JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID
//        WHERE (`Titel` LIKE '%König%' OR `Beschreibung` LIKE '%" + search + "%')
//        AND `Datum` >= '2019-09-22'
//        AND `Uhrzeit` >= '08:00:00'
//        AND `FSK` <= 18
//        AND Gebäude.PLZ = 86153 ;
    }

    private static String getDateAsString()
    {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    private static String getTimeAsString()
    {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(new Date());
    }
}
