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
        return "SELECT Ort.Ortsname, Ort.PLZ, `GebäudeId`, `Straße`, `Hausnummer` FROM Ort INNER JOIN Gebäude ON Ort.PLZ=Gebäude.PLZ ORDER BY `Ortsname`;";
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
        return "SELECT DISTINCT Film.FilmID, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `BildLink` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID WHERE `Datum` >= '" + getDateAsString() +"' LIMIT 3;";
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
                    "AND `Uhrzeit` >= '" + time + "'" +
                    "AND `FSK` <= " + fsk + " ;";
        }
    }

    public static String defaultSearchQuery(String search, String date, String time, int fsk) {
        if(search != "") {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `FSK`, `3D` " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "WHERE (`Titel` LIKE '%" + search + "%' OR `Beschreibung` LIKE '%" + search + "%') " +
                    "AND `Datum` >= '" + date + "' " +
                    "AND `Uhrzeit`>= '" + time + "' " +
                    "AND `FSK` <= " + fsk + " ;";
        } else {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `FSK`, `3D` " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "WHERE `Datum` >= '" + date + "' " +
                    "AND `Uhrzeit`>= '" + time + "' " +
                    "AND `FSK` <= " + fsk + " ;";
        }

    }

    public static String showMovieById(String id, String date, String time, String plz) {

        return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, Sprache.Sprachenname, Kinosaal.SaalID " +
                    "FROM Vorstellung " +
                        "Join Film ON Vorstellung.FilmID = Film.FilmID " +
                        "JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID " +
                        "JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID " +
                        "JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "WHERE Film.FilmID = '" + id + "' " +
                        "AND `Datum` >= '" + date + "' " +
                        "AND `Uhrzeit` >= '" + time + "' " +
                        "AND Gebäude.PLZ = '" + plz + "' ORDER BY `Datum` ASC LIMIT 6; ";

    }

    public static String getGenreNamesById(int id) {
        return "Select Genrebezeichnung FROM FilmGenre " +
                "JOIN Genre ON FilmGenre.GenreID = Genre.GenreID " +
                "Where FilmID = " + id + " ;";
    }

    public static String getSpracheById(int id) {
        return "Select `Sprachenname` FROM Filmsprache " +
        "JOIN Sprache ON Sprache.SprachID = Filmsprache.SprachID " +
        "Where `FilmID` = " + id + " ;";
    }

    public static String getKinosByName(String stadt){
        return "SELECT `Straße`, `Hausnummer`, Gebäude.PLZ, `Ortsname` FROM Gebäude JOIN Ort ON Gebäude.PLZ = Ort.PLZ WHERE Ortsname = '"+ stadt +"' ;";
    }

    public static String getSaalById(int id) {
        return "Select * From Kinosaal Where SaalID = " + id + " ;";
    }

    public static String getMovieById(int id) {
        return "Select * FROM Film Where FilmID = " + id + " ;";
    }

    public static String getVorstellungByID(int id){
        return "SELECT DISTINCT * FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID WHERE VorstellungsID = " + id + " ;";
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
