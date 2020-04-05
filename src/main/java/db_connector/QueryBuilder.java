package db_connector;

import helper.DateFormatter;

import java.util.*;
import java.text.*;

public class QueryBuilder {
    public static String getKundeByPID(int PID) {
        return "SELECT Person.PID as PID, KID,Treuepunkte, Vorname, Nachname, GebDatum, `E-Mail`,Passwort, Straße, Hausnummer, Adresszusatz From Kunde JOIN Person ON Person.PID = Kunde.PID WHERE Person.PID = " + PID + ";";
    }

    public static String getKundeByKID(int KID) {
        return "SELECT Person.PID as PID, KID,Treuepunkte, Vorname, Nachname, GebDatum, `E-Mail`,Passwort, Straße, Hausnummer, Adresszusatz From Kunde JOIN Person ON Person.PID = Kunde.PID WHERE KID = " + KID + ";";
    }

    public static String createLoginQuery(String email, String passwordHash) {
        return "Select Person.PID as PID, Vorname, Nachname, GebDatum, `E-Mail`, KID, Treuepunkte From Person Join Kunde k on Person.PID = k.PID Where `E-Mail` = '" + email + "' AND Passwort = '" + passwordHash + "';";
    }

    public static String createUser(String name, String lastname, Date gebDate, String email, String passwordHash, int hausnummer, String straße, String adresszusatz, int plz) {
        String sqlDateString = DateFormatter.getSQLDate(gebDate);
        return "INSERT INTO Person (`Vorname`, `Nachname`, `GebDatum`, `E-Mail`, `Passwort`, `Hausnummer`, `Straße`, `Adresszusatz`, `PLZ`) VALUES ('" + name + "', '" + lastname + "', '" + sqlDateString + "', '" + email + "', '" + passwordHash + "', '" + hausnummer + "', '" + straße + "', '" + adresszusatz + "', '" + plz + "');\nINSERT INTO Kunde (`PID`, `Treuepunkte`) VALUES ((SELECT `PID` FROM Person WHERE `Vorname` = '" + name + "' AND `Nachname` = '" + lastname + "' AND `GebDatum` = '" + sqlDateString + "' AND `E-Mail` = '" + email + "' AND `Passwort` = '" + passwordHash + "'), 0);";
    }

    public static String showAllCinemas() {
        return "SELECT Ort.Ortsname, Ort.PLZ, `GebäudeId`, `Straße`, `Hausnummer` FROM Ort INNER JOIN Gebäude ON Ort.PLZ=Gebäude.PLZ ORDER BY `Ortsname`;";
    }

    public static String showCinemaImaginationsToday() {
        return "SELECT * FROM Vorstellung WHERE `Datum` = '" + getDateAsString() + "' AND `Uhrzeit` >= '" + getTimeAsString() + "';";
    }

    public static String showCinemaImaginationsThisWeek() {
        return "SELECT * FROM Vorstellung WHERE `Datum` >= '" + getDateAsString() + "' AND `Uhrzeit` >= '" + getTimeAsString() + "';";
    }

    public static String showTitlePageFilms() {
        return "SELECT DISTINCT Film.FilmID, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `BildLink` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID WHERE `Datum` >= '" + getDateAsString() + "' LIMIT 3;";
    }

    public static String showTitelPageFilmsbyPLZ(String plz) {
        return "SELECT DISTINCT Film.FilmID, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `BildLink`, `TrailerLink`, `3D` FROM Vorstellung" +
                " JOIN Film ON Vorstellung.FilmID = Film.FilmID" +
                " JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID" +
                " JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID" +
                " WHERE `Datum` >= '" + getDateAsString() + "'" +
                " AND Gebäude.PLZ = '" + plz + "'" +
                " LIMIT 3;";
    }

    public static String showAllFilmInfos(String filmTitel) {
        return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE `Titel` = '" + filmTitel + "'";
    }

    public static String showGenresForFilmTitle(String filmTitel) {
        return "SELECT `Genrebezeichnung`, `Deskriptor` FROM Genre, Film, FilmGenre WHERE FilmGenre.GenreID = Genre.GenreID AND Film.FilmID = FilmGenre.FilmID AND `Titel` = '" + filmTitel + "';";
    }

    public static String showGenresForFilmID(int filmID) {
        return "SELECT `Genrebezeichnung`, `Deskriptor` FROM Genre, Film, FilmGenre WHERE FilmGenre.GenreID = Genre.GenreID AND Film.FilmID = FilmGenre.FilmID AND Film.FilmID = " + filmID + ";";
    }

    public static String defaultSearchQuery(String search, String date, String time, int fsk, String plz) {
        if (search != "") {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D` " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID " +
                    "JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID " +
                    "WHERE (`Titel` LIKE '%" + search + "%' OR `Beschreibung` LIKE '%" + search + "%') " +
                    "AND concat(`Datum`,  ' ', `Uhrzeit`) >= '" + date + " " + time + "' " +
                    "AND Gebäude.PLZ = '" + plz + "' " +
                    "AND `FSK` <= " + fsk + " ;";
        } else {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D` " +
                    "FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                    "JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID " +
                    "JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID " +
                    "WHERE concat(`Datum`,  ' ', `Uhrzeit`) >= '" + date + " " + time + "' " +
                    "AND Gebäude.PLZ = '" + plz + "' " +
                    "AND `FSK` <= " + fsk + " ;";
        }
    }

    public static String genreSearchQuery(String search, String date, String time, int fsk, String plz, int genreID) {
        if (search != "") {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D`, Genre.GenreID FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID JOIN FilmGenre ON Film.FilmID = FilmGenre.FilmID JOIN Genre On Genre.GenreID = FilmGenre.GenreID WHERE (`Titel` LIKE '%" + search + "%' OR `Beschreibung` LIKE '%" + search + "%') AND concat(`Datum`,  ' ', `Uhrzeit`) >= '" + date + " " + time + "' AND Gebäude.PLZ = '" + plz + "' AND `FSK` <= " + fsk + " AND Genre.GenreID = " + genreID + " ;";
        } else {
            return "SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D`, Genre.GenreID FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID JOIN FilmGenre ON Film.FilmID = FilmGenre.FilmID JOIN Genre On Genre.GenreID = FilmGenre.GenreID WHERE concat(`Datum`,  ' ', `Uhrzeit`) >= '" + date + " " + time + "' AND Gebäude.PLZ = '" + plz + "' AND `FSK` <= " + fsk + " AND Genre.GenreID = " + genreID + " ;";
        }

    }

    public static String getGenres() {
        return "SELECT DISTINCT `GenreID`, `Genrebezeichnung`, `Deskriptor` FROM Genre;";
    }

    public static String showMovieById(String id, String date, String time, String plz) {
                return "SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, Sprache.Sprachenname, Kinosaal.SaalID " +
                "FROM Vorstellung " +
                "JOIN Film ON Vorstellung.FilmID = Film.FilmID " +
                "JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID " +
                "JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID " +
                "JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID " +
                "WHERE Film.FilmID = '" + id + "' " +
                "AND concat(`Datum`,  ' ', `Uhrzeit`) >= '" + date + " " + time + "' " +
                "AND Gebäude.PLZ = '" + plz + "' ORDER BY `Datum`, `Uhrzeit` ASC LIMIT 6;";

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

    public static String getKinosByName(String stadt) {
        return "SELECT `Straße`, `Hausnummer`, Gebäude.PLZ, `Ortsname` FROM Gebäude JOIN Ort ON Gebäude.PLZ = Ort.PLZ WHERE Ortsname = '" + stadt + "' ;";
    }

    public static String getSaalById(int id) {
        return "Select * From Kinosaal Where SaalID = " + id + " ;";
    }

    public static String getSitzplanBySaalID(int id) {
        return "SELECT Kinosaal.SaalID as SaalID, GebäudeID, Saalbezeichnung, Sitzplan.SitzplanID as SitzplanID, SitzplatzID, Reihe, Nummer, Sitzklasse FROM Cineflex.Kinosaal JOIN Cineflex.Sitzplan ON Sitzplan.SaalID = Kinosaal.SaalID JOIN Cineflex.Sitz ON Sitzplan.SitzplanID = Sitz.SitzplanID WHERE Kinosaal.SaalID = " + id + ";";
    }

    public static String getMovieById(int id) {
        return "Select * FROM Film Where FilmID = " + id + " ;";
    }

    public static String getVorstellungByID(int id) {
        return "SELECT VorstellungsID, Datum, Uhrzeit, Film.FilmID as FilmID, Vorstellung.SaalID as SaalID, Sprache.SprachID as SprachID, Titel, Beschreibung, Dauer, FSK, 3D, BildLink, TrailerLink, Grundpreis, Sprachenname, GebäudeID, Saalbezeichnung FROM Cineflex.Vorstellung JOIN Cineflex.Film ON Vorstellung.FilmID = Film.FilmID JOIN Cineflex.Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Cineflex.Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID WHERE VorstellungsID = " + id + " ;";
    }

    public static String getGrundPreis(int id) {
        return "SELECT `Grundpreis`, `Dauer`, `3D`FROM Film WHERE `FilmId` =" + id + ";";
    }

    public static String getPreisveränderungen() {
        //return "SELECT * FROM Preisänderung WHERE `PreisänderungsID` = 4 OR `PreisänderungsID` = 5;";
        return "SELECT * FROM Preisänderung WHERE grundpreis_relevant = 1;";
    }

    public static String getPreiseLaenge() {
        return "SELECT COUNT(*) as laenge FROM Preisänderung;";
    }

    public static String getPreiseInfos() {
        //return "SELECT * FROM Preisänderung WHERE Änderungsbeschreibung != 'Logenaufpreis' AND Änderungsbeschreibung != '3D-Aufschlag' AND Änderungsbeschreibung != 'Überlängenaufschlag';";
        return "SELECT * FROM Preisänderung WHERE grundpreis_relevant IS NULL;";
    }

    public static String getVorstellungByIdPLZ(int id) {
        return "SELECT VorstellungsID, Datum, Uhrzeit, Film.FilmID as FilmID, Vorstellung.SaalID as SaalID, Sprache.SprachID as SprachID, Titel, Beschreibung, Dauer, FSK, 3D, BildLink, TrailerLink, Grundpreis, Sprachenname, Kinosaal.GebäudeID, Saalbezeichnung, PLZ FROM Cineflex.Vorstellung JOIN Cineflex.Film ON Vorstellung.FilmID = Film.FilmID JOIN Cineflex.Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Cineflex.Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID Join Cineflex.Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID WHERE VorstellungsID = " + id + ";";
    }

    public static String getSitzById(int id) {
        return "Select * From Sitz Where SitzplatzID = " + id + ";";
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

    public static String aendereProfil(String vorname, String nachname, String email, String passwort, String strasse, int hausnummer, int PID) {
        return "UPDATE Person SET Vorname='" + vorname + "', Nachname='" + nachname + "',`E-Mail`='" + email + "',Passwort='" + passwort + "',Straße='" + strasse + "',Hausnummer=" + hausnummer + "  WHERE PID = " + PID + ";";
    }

    public static String getBuchungsbelegByKIDandTimestamp(int KID, String timestamp) {
        return "Select * From Buchungsbeleg Where `KID` = " + KID + " AND `Zeitstempel` = '" + timestamp + "';";
    }

    public static String getTimedOutSitzsperre(int minuteTimeDiff, int id) {
        Date date = new Date();

        return "Select * From Sitzsperre Where Timestampdiff(Minute, Zeitstempel, '" + DateFormatter.getSQLDateAndTime(date) + "') < " + minuteTimeDiff + " AND VorstellungsID = " + id + ";";
    }

    public static String deleteTimedOutSitzSperre(int minuteTimeDiff) {
        Date date = new Date();

        return "Delete From Sitzsperre Where Timestampdiff(Minute, Zeitstempel, '" + DateFormatter.getSQLDateAndTime(date) + "') > " + minuteTimeDiff + ";";
    }

    public static String deleteSitzsperreByVorstellung(int vorstellungsID) {
        return "Delete From Sitzsperre Where VorstellungsID = " + vorstellungsID + ";";
    }

    public static String createSitzsperre(int sitzplatzID, int vorstellungsID, int KID, Date date) {
        return "Insert into Sitzsperre(SitzplatzID, VorstellungsID, KID, Zeitstempel) VALUES( " + sitzplatzID + ", " + vorstellungsID + ", " + KID + ", '" + DateFormatter.getSQLDateAndTime(date) + "');";
    }

    public static String getBuchungsbelegByBNR(int BNR) {
        return "Select * From Buchungsbeleg Where BNR = " + BNR + ";";
    }

    public static String getBuchungsbelegeByKID(int KID) {
        return "Select * from Buchungsbeleg Where KID = " + KID + ";";
    }

    public static String createReservierungsbeleg(int KID, int vorstellungsID, float preis, String timestamp) {
        return "Insert INTO Reservierungsbeleg (RNR, KID, VorstellungsID, Preis, Zeitstempel) VALUES (NULL, " + KID + ", " + vorstellungsID + ", " + preis + ", '" + timestamp + "');";
    }


    public static String getReservierungsbelegByKIDandTimestamp(int KID, String timestamp) {
        return "Select * From Reservierungsbeleg Where `KID` = " + KID + " AND `Zeitstempel` = '" + timestamp + "';";
    }

    public static String createReservierungsposition(int posID, int RNR, int sitzID) {
        return "Insert Into Reservierungsposition (PositionsID, RNR, SitzID) VALUES ( " + posID + ", " + RNR + ", " + sitzID + ");";
    }

    public static String createPreisänderungReservierung(int posID, int RNR, int preisVerID) {
        return "Insert Into PreisänderungReservierung (PositionsID, RNR, PreisänderungsID) Values ( " + posID + ", " + RNR + ", " + preisVerID + ") ;";
    }

    public static String getReservierungsbelegByKID(int KID) {
        return "Select * From Reservierungsbeleg Where KID = " + KID + ";";
    }

    public static String getReservierungsbelegByRNR(int RNR) {
        return "Select * From Reservierungsbeleg Where RNR = " + RNR + ";";
    }

    public static String getUserForRegistration(String firstname, String lastname, String gebDate, String email) {
        return "SELECT * FROM Person WHERE `Vorname` = '" + firstname + "' AND `Nachname` = '" + lastname + "' AND `GebDatum` = '" + gebDate + "' AND `E-Mail` = '" + email + "';";
    }

    public static String getUserByEmail(String email) {
        return "Select * From Person Where `E-Mail` = '" + email + "';";
    }

    public static String getPreisänderungByID(int ID) {
        return "Select * From `Preisänderung` Where `PreisänderungsID` = " + ID + ";";
    }

    public static String getGebaeudeById(int id) {
        return "Select * from `Gebäude` Where `GebäudeID` = " + id + ";";
    }

    public static String getBookedSeats(int vorstellungsID) {
        return "Select Buchungsposition.* from Buchungsposition Join Buchungsbeleg On Buchungsposition.BNR = Buchungsbeleg.BNR Where Buchungsbeleg.VorstellungsID = " + vorstellungsID + " AND Not Exists (Select BNR FROM Buchungsstornierung Where Buchungsstornierung.BNR = Buchungsposition.BNR);";
    }

    public static String getReservedSeats(int vorstellungsID) {
        return "Select Reservierungsposition.* from Reservierungsposition Join Reservierungsbeleg On Reservierungsposition.RNR = Reservierungsbeleg.RNR Where Reservierungsbeleg.VorstellungsID = " + vorstellungsID + " AND Not Exists (Select RNR FROM Reservierungsstornierung Where Reservierungsstornierung.RNR = Reservierungsposition.RNR);";
    }

    public static String getBuchungsPositionenByBNR(int BNR) {
        return "Select * From Buchungsposition Where BNR = " + BNR + ";";
    }

    public static String getReservierungsPositionenByRNR(int RNR) {
        return "Select * From Reservierungsposition Where RNR = " + RNR + ";";
    }

    public static String getOrtByPLZ(int plz) {
        return "Select * From Ort Where PLZ = " + plz + ";";
    }

    public static String getJustCreatedBuchung(int KID) {
        //return "SELECT BNR, KID, VorstellungsID, Preis, MAX(Zeitstempel) as `Zeitstempel` FROM `Buchungsbeleg` WHERE KID = " + KID;
        return "SELECT * FROM `Buchungsbeleg` WHERE KID = " + KID + " ORDER BY Zeitstempel DESC LIMIT 1";
    }

    public static String getJustCreatedReservierung(int KID) {
        return "SELECT * FROM `Reservierungsbeleg` WHERE KID = " + KID + " ORDER BY Zeitstempel DESC LIMIT 1";
    }

    public static String createBuchungsStornierung(int BNR) {
        return "Insert Into Buchungsstornierung (BNR) VALUES (" + BNR + ");";
    }

    public static String createReservierungsStornierung(int RNR) {
        return "Insert Into Reservierungsstornierung (RNR) VALUES (" + RNR + ");";

    }
    public static String createFilm(String titel, String beschreibung, int dauer, int FSK, int DDD, String BildLink, String TrailerLink, float Grundpreis){
        return "Insert Into Film (Titel, Beschreibung, Dauer, FSK, 3D, BildLink, TrailerLink, Grundpreis) VALUES ('" + titel + "','" + beschreibung + "' , " + dauer + " , "+ FSK + ", " + DDD + ",'" + BildLink + "','" + TrailerLink + "'," + Grundpreis +");";
    }

    public static String getBuchungsStornierungByStrnNR(int StrnNR) {
        return "Select * from Buchungsstornierung Where StrnNR = " + StrnNR + ";";
    }

    public static String getBuchungsStornierungByBNR(int BNR) {
        return "Select * from Buchungsstornierung Where BNR = " + BNR + ";";
    }

    public static String getReservierungsStornierungByRNR(int RNR) {
        return "Select * from Reservierungsstornierung Where RNR = " + RNR + ";";
    }

    public static String getReservierungsStornierungByStrnNR(int StrnNR) {
        return "Select * from Reservierungsstornierung Where StrnNR = " + StrnNR + ";";
    }

    public static String getBuchungsStornierungByKID(int KID) {
        return "Select * From Buchungsstornierung Join Buchungsbeleg On `Buchungsstornierung.BNR` = `buchungsbeleg.BNR` Where KID = " + KID + ";";
    }

    public static String getReservierungsStornierungByKID(int KID) {
        return "Select * From Reservierungsstornierung Join Buchungsbeleg On `Buchungsstornierung.BNR` = `Buchungsbeleg.BNR` Where KID = " + KID + ";";
    }

    public static String addTreuepunkte(int KID, int punkte) {
        return "Update Kunde Set Treuepunkte = Treuepunkte + " + punkte + " Where KID = " + KID + ";";
    }

    public static String subtractTreuepunkte(int KID, int punkte) {
        return "Update Kunde Set Treuepunkte = Treuepunkte - " + punkte + " Where KID = " + KID + ";";
    }

    public static String createStayLoggedIn(String id, String email, String passwordHash) {
        return "Insert Into stay_logged_in (id, passwordHash, `E-Mail`) Values ('" + id + "', '" + passwordHash + "', '" + email + "');";
    }

    public static String deleteAccount(int PID)
    {
        return "DELETE FROM Kunde WHERE PID = "+PID+";\nDELETE FROM Person WHERE PID = "+PID+";";
    }

    public static String deleteStayLoggedIn(String id) {
        return "Delete From stay_logged_in Where id = '" + id + "';";
    }

    public static String getStayLoggedIn(String id) {
        return "Select * From stay_logged_in Where id = '" + id + "';";
    }

    private static String getDateAsString() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(new Date());
    }

    private static String getTimeAsString() {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(new Date());
    }
}
