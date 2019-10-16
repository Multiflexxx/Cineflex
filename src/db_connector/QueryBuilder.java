package db_connector;

import java.util.*;
import java.text.*;

public class QueryBuilder {
    public static String createLoginQuery(String email, String passwordHash)
    {
        return "Select person.PID as PID, Vorname, Nachname, GebDatum, `E-Mail`, KID, Treuepunkte From person Join kunde k on person.PID = k.PID Where `E-Mail` = '"  + email + "' AND Passwort = '" + passwordHash + "';";
    }

    public static String createUser(String name, String lastname, String gebDate, String email, String passwordHash, String hausnummer, String straße, String adresszusatz)
    {
        return "INSERT INTO Person (`Vorname`, `Nachname`, `GebDatum`, `E-Mail`, `Passwort`, `Hausnummer`, `Straße`, `Adresszusatz`) VALUES ('"+name+"', '"+lastname+"', '"+gebDate+"', '"+email+"', '"+passwordHash+"', '"+hausnummer+"', '"+straße+"', '"+adresszusatz+"'); \n INSERT INTO Kunde (`PID`, `Treuepunkte`) VALUES ((SELECT `PID` FROM Person WHERE `Vorname` = '"+name+"' AND `Nachname` = '"+lastname+"' AND `GebDatum` = '"+gebDate+"' AND `E-Mail` = '"+email+"' AND `Passwort` = '"+passwordHash+"'), 0);";
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

    public static String showTitelPageFilmsbyPLZ(String plz)
    {
        return "SELECT DISTINCT Film.FilmID, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `BildLink`, `TrailerLink`, `3D` FROM Vorstellung" +
                " JOIN Film ON Vorstellung.FilmID = Film.FilmID" +
                " JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID" +
                " JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID" +
                " WHERE `Datum` >= '" + getDateAsString() +"'" +
                " AND Gebäude.PLZ = '"+ plz + "'" +
                " LIMIT 3;";
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

    public static String defaultSearchQuery(String search, String date, String time, int fsk, String plz) {
        if(search != "") {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D` " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID " +
                    "JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID " +
                    "WHERE (`Titel` LIKE '%" + search + "%' OR `Beschreibung` LIKE '%" + search + "%') " +
                    "AND `Datum` >= '" + date + "' " +
                    "AND `Uhrzeit`>= '" + time + "' " +
                    "AND Gebäude.PLZ = '"+ plz + "' " +
                    "AND `FSK` <= " + fsk + " ;";
        } else {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D` " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID " +
                    "JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID " +
                    "WHERE `Datum` >= '" + date + "' " +
                    "AND `Uhrzeit`>= '" + time + "' " +
                    "AND Gebäude.PLZ = '"+ plz + "' " +
                    "AND `FSK` <= " + fsk + " ;";
        }

    }

  public static String genreSearchQuery(String search, String date, String time, int fsk, String plz, int genreID) {
    if(search != "") {
      return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D`, Genre.GenreID FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID JOIN FilmGenre ON Film.FilmID = FilmGenre.FilmID JOIN Genre On Genre.GenreID = FilmGenre.GenreID WHERE (`Titel` LIKE '%" + search + "%' OR `Beschreibung` LIKE '%" + search + "%') AND `Datum` >= '" + date + "' AND `Uhrzeit`>= '" + time + "' AND Gebäude.PLZ = '" + plz + "' AND `FSK` <= "+ fsk + " AND Genre.GenreID = " + genreID + " ;";
    } else {
      return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D`, Genre.GenreID FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID JOIN FilmGenre ON Film.FilmID = FilmGenre.FilmID JOIN Genre On Genre.GenreID = FilmGenre.GenreID WHERE `Datum` >= '" + date + "' AND `Uhrzeit`>= '" + time + "' AND Gebäude.PLZ = '" + plz + "' AND `FSK` <= "+ fsk + " AND Genre.GenreID = " + genreID + " ;";
    }

  }

    public static String getGenres(){
       return  "SELECT DISTINCT `GenreID`, `Genrebezeichnung`, `Deskriptor` FROM Genre";
    }

    public static String showMovieById(String id, String date, String time, String plz) {


//        SELECT `VorstellungsID`, concat(`Datum`,  ' ', `Uhrzeit`) AS timestamp, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, Sprache.Sprachenname, Kinosaal.SaalID FROM Vorstellung
//        Join Film ON Vorstellung.FilmID = Film.FilmID
//        JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID
//        JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID
//        JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID
//        WHERE Film.FilmID = '11'
//        AND concat(`Datum`,  ' ', `Uhrzeit`) >= '2019-10-11 08:28'
//        AND Gebäude.PLZ = '86153' ORDER BY `Datum` ASC LIMIT 6;


        return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, Sprache.Sprachenname, Kinosaal.SaalID " +
                    "FROM Vorstellung " +
                        "JOIN Film ON Vorstellung.FilmID = Film.FilmID " +
                        "JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID " +
                        "JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID " +
                        "JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                        "WHERE Film.FilmID = '" + id + "' " +
                        "AND concat(`Datum`,  ' ', `Uhrzeit`) >= '" + date + " " + time + "' " +
                        "AND Gebäude.PLZ = '" + plz + "' ORDER BY `Datum` ASC LIMIT 6;";

    }

    public static String getGenreNamesById(int id) {
        return "Select `Genrebezeichnung` FROM FilmGenre " +
                "JOIN Genre ON FilmGenre.GenreID = Genre.GenreID " +
                "Where FilmID = " + id + ";";
    }

    public static String getGenreByID(int id) {
        return "Select `Genrebezeichnung` FROM FilmGenre " +
            "JOIN Genre ON Genre.GenreID = Filmgenre.GenreID " +
            "Where `GenreID` = " + id + " ;";
    }

    public static String getSpracheById(int id) {
        return "Select `Sprachenname` FROM Filmsprache " +
        "JOIN Sprache ON Sprache.SprachID = Filmsprache.SprachID " +
        "Where `FilmID` = " + id + ";";
    }

    public static String getKinosByName(String stadt){
        return "SELECT `Straße`, `Hausnummer`, Gebäude.PLZ, `Ortsname` FROM Gebäude JOIN Ort ON Gebäude.PLZ = Ort.PLZ WHERE Ortsname = '"+ stadt +"' ;";
    }

    public static String getSaalById(int id) {
        return "Select * From Kinosaal Where SaalID = " + id + " ;";
    }

    public static String getSitzplanBySaalID(int id){
        return "SELECT Kinosaal.SaalID as SaalID, GebäudeID, Saalbezeichnung, Sitzplan.SitzplanID as SitzplanID, SitzplatzID, Reihe, Nummer, Sitzklasse FROM Cineflex.Kinosaal JOIN Cineflex.Sitzplan ON Sitzplan.SaalID = Kinosaal.SaalID JOIN Cineflex.Sitz ON Sitzplan.SitzplanID = Sitz.SitzplanID WHERE Kinosaal.SaalID = " + id + ";";
    }

    public static String getMovieById(int id) {
        return "Select * FROM Film Where FilmID = " + id + " ;";
    }

    public static String getVorstellungByID(int id){
       return "SELECT VorstellungsID, Datum, Uhrzeit, Film.FilmID as FilmID, Vorstellung.SaalID as SaalID, Sprache.SprachID as SprachID, Titel, Beschreibung, Dauer, FSK, 3D, BildLink, TrailerLink, Grundpreis, Sprachenname, GebäudeID, Saalbezeichnung FROM Cineflex.Vorstellung JOIN Cineflex.Film ON Vorstellung.FilmID = Film.FilmID JOIN Cineflex.Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Cineflex.Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID WHERE VorstellungsID = " + id + " ;";
    }

    public static String getGrundPreis(int id)
    {
        return "SELECT `Grundpreis`, `Dauer`, `3D`FROM Film WHERE `FilmId` =" + id + ";";
    }

    public static String getPreisveränderungen()
    {
        return "SELECT * FROM Preisänderung WHERE `PreisänderungsID` = 4 OR `PreisänderungsID` = 5;";
    }

    public static String getPreiseLaenge()
    {
        return "SELECT COUNT(*) as laenge FROM Preisänderung;";
    }

    public static String getPreiseInfos()
    {
        return "SELECT * FROM Preisänderung WHERE Änderungsbeschreibung != 'Logenaufpreis' AND Änderungsbeschreibung != '3D-Aufschlag' AND Änderungsbeschreibung != 'Überlängenaufschlag';";
    }

    public static String getVorstellungByIdPLZ(int id) {
        return "SELECT VorstellungsID, Datum, Uhrzeit, Film.FilmID as FilmID, Vorstellung.SaalID as SaalID, Sprache.SprachID as SprachID, Titel, Beschreibung, Dauer, FSK, 3D, BildLink, TrailerLink, Grundpreis, Sprachenname, Kinosaal.GebäudeID, Saalbezeichnung, PLZ FROM Cineflex.Vorstellung JOIN Cineflex.Film ON Vorstellung.FilmID = Film.FilmID JOIN Cineflex.Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Cineflex.Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID Join Cineflex.Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID WHERE VorstellungsID = " + id + ";";
    }

    public static String getSitzById(int id) {
        return "Select * From sitz Where SitzplatzID = " + id + ";";
    }

    public static String createBuchungsBeleg(int KID, int vorstellungsID, float preis, String timestamp) {
        return "Insert INTO Buchungsbeleg (BNR, KID, VorstellungsID, Preis, Zeitstempel) VALUES (NULL, " + KID + ", " + vorstellungsID + ", " + preis + ", '" + timestamp + "');";
    }

    public static String createBuchungsposition(int posID, int BNR, int sitzID) {
        return "Insert INTO Buchungsposition (PositionsID, BNR, SitzID) VALUES ( " + posID + ", " + BNR + ", " + sitzID + ");";
    }

    public static String createPreisänderungBuchung(int posID, int BNR, int preisVerID) {
        return "Insert Into PreisänderungBuchung (PositionsID, BNR, PreisänderungsID) Values ( " + posID + ", " + BNR + ", " + preisVerID + ") ;";
    }

    public static String getBuchungsbelegByKIDandTimestamp(int KID, String timestamp) {
        return "Select * From Buchungsbeleg Where `KID` = " + KID + " AND `Zeitstempel` = '" + timestamp + "';";
    }

    // NOT USED
    /*
    public static String getSeatInfo(int vorstellungsID)
    {
        return "SELECT Sitz.SitzplatzID, Sitz.SitzplanID, `Reihe`, `Nummer`, `Sitzklasse`, Sitzplan.SaalID, Vorstellung.VorstellungsID FROM Sitz JOIN Sitzplan On Sitz.SitzplanID = Sitzplan.SitzplanID JOIN Vorstellung ON Vorstellung.SaalID = Sitzplan.SaalID WHERE Vorstellung.VorstellungsID = "+ vorstellungsID +";";
    }*/


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
