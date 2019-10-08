package junit_test;

import Password.PassMD5;
import db_connector.Connector;
import db_connector.QueryBuilder;
import handler.RegistrationHandler;
import handler.SingleMovieHandler;
import helper.DateFormatter;
import oo.*;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.mockito.Mockito.when;

public class Test {

    // Create Mock Data for HttpServlets
    @Mock
    HttpServletRequest request1;

    @Mock
    HttpServletResponse response1;

    @Mock
    HttpServletRequest request2;

    @Mock
    HttpServletResponse response2;

    @Mock
    HttpServletRequest request3;

    @Mock
    HttpServletResponse response3;

    @Mock
    HttpServletRequest request4;

    @Mock
    HttpServletResponse response4;

    @Mock
    HttpServletRequest request5;

    @Mock
    HttpServletResponse response5;

    @Mock
    HttpServletRequest request6;

    @Mock
    HttpServletResponse response6;

    @InjectMocks
    Connector connector;
    @Mock
    Connection mockConnection;
    @Mock
    Statement mockStatement;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    // TESTS FOR OO

    // Test for class Sitz
    @org.junit.Test
    public void testeSitz()
    {
        Sitz sitz = new Sitz(2, 5, 'A', 'B', "Mein Test Sitz", 5.99f);
        Assert.assertEquals(2, sitz.getSitzID());
        Assert.assertEquals(5, sitz.getNummer());
        Assert.assertEquals('A', sitz.getReihe());
        Assert.assertEquals('B', sitz.getSitzklasse());
        Assert.assertEquals("Mein Test Sitz", sitz.getBeschreibung());
        Assert.assertEquals(5.99f, sitz.getGrundpreis(), 0); // delta needed for floating point numbers

        sitz.setSitzID(5);
        Assert.assertEquals(5, sitz.getSitzID());
        sitz.setNummer(42);
        Assert.assertEquals(42, sitz.getNummer());
        sitz.setReihe('F');
        Assert.assertEquals('F', sitz.getReihe());
        sitz.setSitzklasse('P');
        Assert.assertEquals('P', sitz.getSitzklasse());
        sitz.setBeschreibung("TEST");
        Assert.assertEquals("TEST", sitz.getBeschreibung());
        sitz.setGrundpreis(3.99f);
        Assert.assertEquals(3.99f, sitz.getGrundpreis(), 0);
    }
    //----

    // Tests for childs of class Person
    // Tests for class Kunde
    @org.junit.Test
    public void testeKunde()
    {
        Kunde kunde = new Kunde("68165", "kunde@mail.com", "Max", "Mustermann", "sicheresPasswort", "1999-08-08", 12, "Lange Straße", 2, 3, 0);

        Assert.assertEquals("68165", kunde.getPlz());
        Assert.assertEquals("kunde@mail.com", kunde.getEmail());
        Assert.assertEquals("Max", kunde.getVorname());
        Assert.assertEquals("Mustermann", kunde.getNachname());
        Assert.assertEquals("sicheresPasswort", kunde.getPasswort());
        Assert.assertEquals("1999-08-08", kunde.getDatum());
        Assert.assertEquals(12, kunde.getPersonenID());
        Assert.assertEquals("Lange Straße", kunde.getStrasse());
        Assert.assertEquals(2, kunde.getHausnummer());
        Assert.assertEquals(3, kunde.getKundenID());
        Assert.assertEquals(0, kunde.getTreuepunkte());

        kunde.setTreuepunkte(12);
        Assert.assertEquals(12, kunde.getTreuepunkte());

        kunde.setKundenID(5);
        Assert.assertEquals(5, kunde.getKundenID());

        kunde.setPlz("68159");
        Assert.assertEquals("68159", kunde.getPlz());
        kunde.setEmail("neueMail@web.de");
        Assert.assertEquals("neueMail@web.de", kunde.getEmail());
        kunde.setVorname("Miriam");
        Assert.assertEquals("Miriam", kunde.getVorname());
        kunde.setNachname("Musterfrau");
        Assert.assertEquals("Musterfrau", kunde.getNachname());
        kunde.setPasswort("sicheresPasswort123");
        Assert.assertEquals("sicheresPasswort123", kunde.getPasswort());
        kunde.setDatum("1999-06-06");
        Assert.assertEquals("1999-06-06", kunde.getDatum());
        kunde.setPersonenID(15);
        Assert.assertEquals(15, kunde.getPersonenID());
        kunde.setStrasse("Kurze Straße");
        Assert.assertEquals("Kurze Straße", kunde.getStrasse());
        kunde.setHausnummer(5);
        Assert.assertEquals(5, kunde.getHausnummer());
        kunde.setKundenID(19);
        Assert.assertEquals(19, kunde.getKundenID());
        kunde.setTreuepunkte(1337);
        Assert.assertEquals(1337, kunde.getTreuepunkte());

        Kunde kunde2 = new Kunde();
        Assert.assertNotEquals(null, kunde2);
    }

    // Tests for class Admin
    @org.junit.Test
    public void testeAdmin()
    {
        Admin admin = new Admin("68165", "admin@cineflex.com", "Jonathan", "Muster", "sehrSicheresPasswort", "1999-08-08", 13, 55);

        Assert.assertEquals("68165", admin.getPlz());
        Assert.assertEquals("admin@cineflex.com", admin.getEmail());
        Assert.assertEquals("Jonathan", admin.getVorname());
        Assert.assertEquals("Muster", admin.getNachname());
        Assert.assertEquals("sehrSicheresPasswort", admin.getPasswort());
        Assert.assertEquals("1999-08-08", admin.getDatum());
        Assert.assertEquals(13, admin.getPersonenID());
        Assert.assertEquals(55, admin.getMitarbeiterID());

        admin.setMitarbeiterID(56);
        Assert.assertEquals(56, admin.getMitarbeiterID());

        Admin admin2 = new Admin(99);

        Assert.assertEquals(99, admin2.getMitarbeiterID());

        admin2.setPlz("68165");
        Assert.assertEquals("68165", admin2.getPlz());
        admin2.setEmail("neueMail2@web.de");
        Assert.assertEquals("neueMail2@web.de", admin2.getEmail());
        admin2.setVorname("Manuel");
        Assert.assertEquals("Manuel", admin2.getVorname());
        admin2.setNachname("Mann");
        Assert.assertEquals("Mann", admin2.getNachname());
        admin2.setPasswort("ultraSicheresPasswort123");
        Assert.assertEquals("ultraSicheresPasswort123", admin2.getPasswort());
        admin2.setDatum("1999-06-09");
        Assert.assertEquals("1999-06-09", admin2.getDatum());
        admin2.setPersonenID(42);
        Assert.assertEquals(42, admin2.getPersonenID());
        admin2.setStrasse("Kleine Straße");
        Assert.assertEquals("Kleine Straße", admin2.getStrasse());
        admin2.setHausnummer(69);
        Assert.assertEquals(69, admin2.getHausnummer());

        Admin admin3 = new Admin();
        Assert.assertNotEquals(null, admin3);
    }
    //----

    // Tests for child classes of Beleg
    // Tests for class Buchungsbeleg
    @org.junit.Test
    public void testeBuchungsbeleg()
    {
        Sitz sitz1 = new Sitz(8, 7, 'C', 'L', "Mein Test Sitz 1", 6.99f);
        Sitz sitz2 = new Sitz(9, 9, 'D', 'B', "Mein Test Sitz 2", 4.99f);
        Sitz sitz3 = new Sitz(10, 10, 'D', 'P', "Mein Test Sitz 3", 5.99f);
        Sitz sitz4 = new Sitz(11, 11, 'D', 'P', "Mein Test Sitz 4", 5.99f);
        Sitz sitz5 = new Sitz(12, 12, 'D', 'P', "Mein Test Sitz 5", 5.99f);

        Sitz[] sitzplan = {sitz1, sitz2, sitz3, sitz4, sitz5};

        // Check if seat was added to array and contains same values
        Assert.assertEquals(sitzplan[0], sitz1);
        Assert.assertEquals(sitzplan[0].getGrundpreis(), sitz1.getGrundpreis(), 0);

        Kinosaal kinosaal = new Kinosaal(2, "Saal 1", sitzplan);

        // Check if object created succesfully
        Assert.assertEquals(2, kinosaal.getSaalID());
        Assert.assertEquals("Saal 1", kinosaal.getBezeichnung());
        Assert.assertEquals(sitzplan, kinosaal.getSitzplan());

        String[] genre = {"Drama"};
        String[] sprachen = {"deutsch", "englisch"};

        Film film = new Film("Toy Story 15", "Cooler Film", "/img/5.jpg", "https://youtube.com", 160, 6, 5, true, genre, sprachen);

        Assert.assertEquals("Toy Story 15", film.getTitel());
        Assert.assertEquals(genre, film.getGenre());

        // Create a new date and time for testing
        Date date = null;
        Date time = null;

        try {
             date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-23");
             time = new SimpleDateFormat("HH:mm:ss").parse("19:30:00");
        }
        catch (Exception e)
        {
            Assert.assertEquals(1,0);
        }

        Vorstellung vorstellung = new Vorstellung(2, date, time, "deutsch", film, kinosaal);

        // Check if object created successfully
        Assert.assertEquals(2, vorstellung.getVorstellungsID());
        Assert.assertEquals(date, vorstellung.getDatum());
        Assert.assertEquals(time, vorstellung.getUhrzeit());
        Assert.assertEquals("deutsch", vorstellung.getSprache());
        Assert.assertEquals(film, vorstellung.getFilm());
        Assert.assertEquals(5,vorstellung.getFilm().getFilmID());
        Assert.assertEquals(kinosaal, vorstellung.getSaal());
        Assert.assertEquals(2, vorstellung.getSaal().getSaalID());

        Kunde kunde = new Kunde("68165", "kunde@email.com", "Maximilian", "Mustermann", "sichererPasswortHash", "1999-05-06", 5, "Winkelgasse", 3, 15, 0);

        // Check if object created sucessfully
        Assert.assertEquals("68165", kunde.getPlz());
        Assert.assertEquals(5, kunde.getPersonenID());
        Assert.assertEquals(15, kunde.getKundenID());
        Assert.assertEquals(0, kunde.getTreuepunkte());

        Sitz[] sitzauswahl = {kinosaal.getSitzplan()[0], kinosaal.getSitzplan()[1]};

        // Check if marked seats are set correctly
        Assert.assertEquals(sitz1, sitzauswahl[0]);
        Assert.assertEquals(sitz2, sitzauswahl[1]);

        float preis = 0;

        for(int i = 0; i < sitzauswahl.length; i++)
        {
            preis += sitzauswahl[i].getGrundpreis();
        }

        // Check if price is calculated correctly
        Assert.assertEquals(11.98f, preis, 0);

        // Format time using the DateFormatter class
        String buchungsZeit = DateFormatter.getSQLTime(vorstellung.getUhrzeit());

        // Check if formatting was successfully
        Assert.assertEquals("19:30:00", buchungsZeit);

        // Create new object (main reason for this test)
        Buchungsbeleg buchungsbeleg = new Buchungsbeleg(1, preis, vorstellung, kunde, sitzauswahl, buchungsZeit);

        // Check if class Buchungsbeleg works as expected
        Assert.assertEquals(1, buchungsbeleg.getBelegID());
        Assert.assertEquals(11.98f, buchungsbeleg.getPreis(),0);
        Assert.assertEquals(vorstellung, buchungsbeleg.getVorstellung());
        Assert.assertEquals(DateFormatter.getSQLTime(vorstellung.getUhrzeit()), buchungsbeleg.getUhrzeit());

        Assert.assertEquals(kunde, buchungsbeleg.getKunde());
        Assert.assertEquals(kunde.getTreuepunkte(), buchungsbeleg.getKunde().getTreuepunkte());

        // Set points for customer
        kunde.setTreuepunkte(25);
        buchungsbeleg.setKunde(kunde);
        Assert.assertEquals(25, buchungsbeleg.getKunde().getTreuepunkte());

        Assert.assertEquals(sitzauswahl, buchungsbeleg.getSitzauswahl());
        Assert.assertEquals(sitzauswahl[0], buchungsbeleg.getSitzauswahl()[0]);
        Assert.assertEquals('C', buchungsbeleg.getSitzauswahl()[0].getReihe());

        Assert.assertEquals(buchungsZeit, buchungsbeleg.getUhrzeit());

        // Test setters
        buchungsbeleg.setBelegID(5);
        Assert.assertEquals(5, buchungsbeleg.getBelegID());

        buchungsbeleg.setPreis(5.69f);
        Assert.assertEquals(5.69f, buchungsbeleg.getPreis(),0);

        buchungsbeleg.setKunde(null);
        Assert.assertEquals(null, buchungsbeleg.getKunde());

        Sitz[] sitzauswahl2 = {};

        buchungsbeleg.setSitzauswahl(sitzauswahl2);
        Assert.assertEquals(sitzauswahl2, buchungsbeleg.getSitzauswahl());

        buchungsbeleg.setUhrzeit("15:34:32");
        Assert.assertEquals("15:34:32", buchungsbeleg.getUhrzeit());

        buchungsbeleg.setVorstellung(null);
        Assert.assertEquals(null, buchungsbeleg.getVorstellung());
    }

    // Tests for class Reservierungsbeleg
    @org.junit.Test
    public void testeReservierungsBeleg()
    {
        Sitz sitz1 = new Sitz(1, 7, 'C', 'L', "Mein Test Sitz 1", 6.99f);
        Sitz sitz2 = new Sitz(2, 9, 'D', 'B', "Mein Test Sitz 2", 4.99f);
        Sitz sitz3 = new Sitz(3, 10, 'D', 'P', "Mein Test Sitz 3", 5.99f);
        Sitz sitz4 = new Sitz(4, 11, 'D', 'P', "Mein Test Sitz 4", 5.99f);
        Sitz sitz5 = new Sitz(5, 12, 'D', 'P', "Mein Test Sitz 5", 5.99f);
        Sitz sitz6 = new Sitz(6, 12, 'D', 'P', "Mein Test Sitz 6", 5.99f);


        Sitz[] sitzplan = {sitz1, sitz2, sitz3, sitz4, sitz5, sitz6};

        // Check if seat was added to array and contains same values
        Assert.assertEquals(sitzplan[1], sitz2);
        Assert.assertEquals(sitzplan[1].getGrundpreis(), sitz2.getGrundpreis(), 0);

        Kinosaal kinosaal = new Kinosaal(3, "Saal 3", sitzplan);

        // Check if object created succesfully
        Assert.assertEquals(3, kinosaal.getSaalID());
        Assert.assertEquals("Saal 3", kinosaal.getBezeichnung());
        Assert.assertEquals(sitzplan, kinosaal.getSitzplan());

        String[] genre = {"Drama"};
        String[] sprachen = {"deutsch", "englisch"};

        Film film = new Film("Toy Story 2", "Cooler Film", "/img/5.jpg", "https://youtube.com", 160, 6, 5, true, genre, sprachen);

        Assert.assertEquals("Toy Story 2", film.getTitel());
        Assert.assertEquals(genre, film.getGenre());

        // Create a new date and time for testing
        Date date = null;
        Date time = null;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-24");
            time = new SimpleDateFormat("HH:mm:ss").parse("20:30:00");
        }
        catch (Exception e)
        {
            Assert.assertEquals(1,0);
        }

        Vorstellung vorstellung = new Vorstellung(3, date, time, "englisch", film, kinosaal);

        // Check if object created successfully
        Assert.assertEquals(3, vorstellung.getVorstellungsID());
        Assert.assertEquals(date, vorstellung.getDatum());
        Assert.assertEquals(time, vorstellung.getUhrzeit());
        Assert.assertEquals("englisch", vorstellung.getSprache());
        Assert.assertEquals(film, vorstellung.getFilm());
        Assert.assertEquals(5,vorstellung.getFilm().getFilmID());
        Assert.assertEquals(kinosaal, vorstellung.getSaal());
        Assert.assertEquals(3, vorstellung.getSaal().getSaalID());

        Kunde kunde = new Kunde("32839", "maxi@email.com", "Maximilian", "Mustermann", "sichererPasswortHash", "1999-05-06", 6, "Winkelgasse", 3, 16, 0);

        // Check if object created sucessfully
        Assert.assertEquals("32839", kunde.getPlz());
        Assert.assertEquals(6, kunde.getPersonenID());
        Assert.assertEquals(16, kunde.getKundenID());
        Assert.assertEquals(0, kunde.getTreuepunkte());

        Sitz[] sitzauswahl = {kinosaal.getSitzplan()[1], kinosaal.getSitzplan()[2]};

        // Check if marked seats are set correctly
        Assert.assertEquals(sitz2, sitzauswahl[0]);
        Assert.assertEquals(sitz3, sitzauswahl[1]);

        float preis = 0;

        for(int i = 0; i < sitzauswahl.length; i++)
        {
            preis += sitzauswahl[i].getGrundpreis();
        }

        // Check if price is calculated correctly
        Assert.assertEquals(10.98f, preis, 0);

        // Format time using the DateFormatter class
        String buchungsZeit = DateFormatter.getSQLTime(vorstellung.getUhrzeit());

        // Check if formatting was successfully
        Assert.assertEquals("20:30:00", buchungsZeit);

        // Create new object (main reason for this test)
        Reservierungsbeleg reservierungsbeleg = new Reservierungsbeleg(2, preis, vorstellung, kunde, sitzauswahl, buchungsZeit);

        // Check if class Buchungsbeleg works as expected
        Assert.assertEquals(2, reservierungsbeleg.getBelegID());
        Assert.assertEquals(10.98f, reservierungsbeleg.getPreis(),0);
        Assert.assertEquals(vorstellung, reservierungsbeleg.getVorstellung());
        Assert.assertEquals(DateFormatter.getSQLTime(vorstellung.getUhrzeit()), reservierungsbeleg.getUhrzeit());

        Assert.assertEquals(kunde, reservierungsbeleg.getKunde());
        Assert.assertEquals(kunde.getTreuepunkte(), reservierungsbeleg.getKunde().getTreuepunkte());

        // Set points for customer
        kunde.setTreuepunkte(30);
        reservierungsbeleg.setKunde(kunde);
        Assert.assertEquals(30, reservierungsbeleg.getKunde().getTreuepunkte());

        Assert.assertEquals(sitzauswahl, reservierungsbeleg.getSitzauswahl());
        Assert.assertEquals(sitzauswahl[0], reservierungsbeleg.getSitzauswahl()[0]);
        Assert.assertEquals('D', reservierungsbeleg.getSitzauswahl()[0].getReihe());

        Assert.assertEquals(buchungsZeit, reservierungsbeleg.getUhrzeit());

        // Test setters
        reservierungsbeleg.setBelegID(3);
        Assert.assertEquals(3, reservierungsbeleg.getBelegID());

        reservierungsbeleg.setPreis(7.69f);
        Assert.assertEquals(7.69f, reservierungsbeleg.getPreis(),0);

        reservierungsbeleg.setKunde(null);
        Assert.assertEquals(null, reservierungsbeleg.getKunde());

        Sitz[] sitzauswahlNeu = {};

        reservierungsbeleg.setSitzauswahl(sitzauswahlNeu);
        Assert.assertEquals(sitzauswahlNeu, reservierungsbeleg.getSitzauswahl());

        reservierungsbeleg.setUhrzeit("19:15:23");
        Assert.assertEquals("19:15:23", reservierungsbeleg.getUhrzeit());

        reservierungsbeleg.setVorstellung(null);
        Assert.assertEquals(null, reservierungsbeleg.getVorstellung());
    }
    //----

    // Tests for class DateFormatter
    @org.junit.Test
    public void testeDateFormatter()
    {
        Date date = new Date();

        SimpleDateFormat frontendDate = new SimpleDateFormat("EEE, dd. MMM", new Locale("de", "DE"));
        SimpleDateFormat frontendTime = new SimpleDateFormat("HH:mm", new Locale("de", "DE"));
        SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sqlTime = new SimpleDateFormat("HH:mm:ss");

        Assert.assertEquals(frontendDate.format(date), DateFormatter.getFrontendDate(date));
        Assert.assertEquals(frontendTime.format(date), DateFormatter.getFrontendTime(date));
        Assert.assertEquals(sqlDate.format(date), DateFormatter.getSQLDate(date));
        Assert.assertEquals(sqlTime.format(date), DateFormatter.getSQLTime(date));
    }
    //----

    // Tests for class Film
    @org.junit.Test
    public void testeFilm()
    {
        String[] genre = {"Animation", "Drama"};
        String[] sprachen = {"deutsch", "englisch"};

        Film film = new Film("König der Löwen", "Hier kommt die Beschreibung", "/img/1.jpg", "https://youtube.com/", 160, 6, 1, false, genre, sprachen);

        Assert.assertEquals("König der Löwen", film.getTitel());
        Assert.assertEquals("Hier kommt die Beschreibung", film.getBeschreibung());
        Assert.assertEquals("/img/1.jpg", film.getBildLink());
        Assert.assertEquals("https://youtube.com/", film.getTrailerLink());
        Assert.assertEquals(160, film.getDauer());
        Assert.assertEquals(6, film.getFsk());
        Assert.assertEquals(1, film.getFilmID());
        Assert.assertEquals(false, film.isDreiD());
        Assert.assertEquals(genre, film.getGenre());
        Assert.assertEquals("Animation", film.getGenre()[0]);
        Assert.assertEquals("Drama", film.getGenre()[1]);
        Assert.assertEquals(sprachen, film.getSprache());
        Assert.assertEquals(sprachen[0], film.getSprache()[0]);
        Assert.assertEquals(sprachen[1], film.getSprache()[1]);

        film.setTitel("König der Löwen 2");
        Assert.assertEquals("König der Löwen 2", film.getTitel());
        film.setBeschreibung("Neue Beschreibung");
        Assert.assertEquals("Neue Beschreibung", film.getBeschreibung());
        film.setBildLink("/img/bild1.jpg");
        Assert.assertEquals("/img/bild1.jpg", film.getBildLink());
        film.setTrailerLink("https://vimeo.com");
        Assert.assertEquals("https://vimeo.com", film.getTrailerLink());
        film.setDauer(150);
        Assert.assertEquals(150, film.getDauer());
        film.setFsk(18);
        Assert.assertEquals(18, film.getFsk());
        film.setFilmID(5);
        Assert.assertEquals(5, film.getFilmID());
        film.setDreiD(true);
        Assert.assertEquals(true, film.isDreiD());

        String[] genre2 = {"Komödie"};
        String[] sprachen2 = {"Türkisch"};

        film.setGenre(genre2);
        Assert.assertEquals(genre2, film.getGenre());
        film.setSprache(sprachen2);
        Assert.assertEquals(sprachen2, film.getSprache());

        Film film2 = new Film(5);

        Assert.assertNotEquals(null, film2);
        Assert.assertEquals(5, film2.getFilmID());

        Film film3 = new Film("Neuer Film", "Neue Beschreibung", "/img/4.jpg", "https://youtube.com", 150, 6, 4, false);

        Assert.assertNotEquals(null, film3);
        Assert.assertEquals("Neuer Film", film3.getTitel());
        Assert.assertEquals(4, film3.getFilmID());
    }
    //----

    // Tests for class Kinosaal
    @org.junit.Test
    public void testeKinosaal()
    {
        Sitz sitz1 = new Sitz(2, 5, 'A', 'L', "Mein Test Sitz", 5.99f);
        Sitz sitz2 = new Sitz(3, 6, 'A', 'L', "Mein Test Sitz 2", 5.99f);
        Sitz sitz3 = new Sitz(5, 5, 'B', 'B', "Mein Test Sitz 3", 3.99f);

        Sitz[] sitzplan = {sitz1, sitz2, sitz3};

        Kinosaal kinosaal = new Kinosaal(1, "Saal 1", sitzplan);

        Assert.assertEquals(1, kinosaal.getSaalID());
        Assert.assertEquals("Saal 1", kinosaal.getBezeichnung());
        Assert.assertEquals(sitzplan, kinosaal.getSitzplan());
        Assert.assertEquals(sitz1, kinosaal.getSitzplan()[0]);
        Assert.assertEquals(sitz2.getReihe(), kinosaal.getSitzplan()[0].getReihe());
        Assert.assertEquals(5.99f, kinosaal.getSitzplan()[0].getGrundpreis(),0);

        kinosaal.setSaalID(3);
        Assert.assertEquals(3, kinosaal.getSaalID());
        kinosaal.setBezeichnung("Saal 2");
        Assert.assertEquals("Saal 2", kinosaal.getBezeichnung());

        Sitz sitz4 = new Sitz(8, 7, 'C', 'L', "Mein Test Sitz 4", 7.99f);
        Sitz sitz5 = new Sitz(9, 9, 'D', 'B', "Mein Test Sitz 5", 3.99f);
        Sitz[] sitzplan2 = {sitz4, sitz5};
        kinosaal.setSitzplan(sitzplan2);

        Assert.assertEquals(sitzplan2, kinosaal.getSitzplan());
        Assert.assertEquals(sitzplan2[1].getGrundpreis(), kinosaal.getSitzplan()[1].getGrundpreis(), 0);
    }
    //----

    // Tests for class Vorstellung
    @org.junit.Test
    public void testeVorstellung()
    {
        Date date = null;
        Date time = null;
        Date date2 = null;
        Date time2 = null;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-23");
            time = new SimpleDateFormat("HH:mm:ss").parse("19:30:00");
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-06");
            time2 = new SimpleDateFormat("HH:mm:ss").parse("15:30:00");
        }

        catch (Exception e)
        {
            Assert.assertEquals(1,0);
        }

        Sitz sitz1 = new Sitz(2, 5, 'A', 'L', "Mein Test Sitz", 5.99f);
        Sitz sitz2 = new Sitz(3, 6, 'A', 'L', "Mein Test Sitz 2", 5.99f);
        Sitz sitz3 = new Sitz(5, 5, 'B', 'B', "Mein Test Sitz 3", 3.99f);

        Sitz[] sitzplan = {sitz1, sitz2, sitz3};

        Kinosaal kinosaal = new Kinosaal(1, "Saal 1", sitzplan);

        String[] genre = {"Animation", "Drama"};
        String[] sprachen = {"deutsch", "englisch"};

        Film film = new Film("König der Löwen", "Hier kommt die Beschreibung", "/img/1.jpg", "https://youtube.com/", 160, 6, 1, false, genre, sprachen);

        Vorstellung vorstellung1 = new Vorstellung(1, date, time, sprachen[1], film, kinosaal );

        Assert.assertEquals(1, vorstellung1.getVorstellungsID());
        Assert.assertEquals(date, vorstellung1.getDatum());
        Assert.assertEquals(time, vorstellung1.getUhrzeit());
        Assert.assertEquals("englisch", vorstellung1.getSprache());
        Assert.assertEquals(film, vorstellung1.getFilm());
        Assert.assertEquals(kinosaal, vorstellung1.getSaal());

        vorstellung1.setSprache("englisch");
        Assert.assertEquals("englisch", vorstellung1.getSprache());

        vorstellung1.setVorstellungsID(2);
        Assert.assertEquals(2, vorstellung1.getVorstellungsID());

        Sitz sitz4 = new Sitz(6, 5, 'A', 'L', "Mein Test Sitz 4", 6.99f);
        Sitz sitz5 = new Sitz(7, 6, 'B', 'L', "Mein Test Sitz 5", 5.99f);
        Sitz sitz6 = new Sitz(8, 5, 'B', 'B', "Mein Test Sitz 6", 3.99f);

        Sitz[] sitzplan2 = {sitz4, sitz5, sitz6};

        Kinosaal kinosaal2 = new Kinosaal(1, "Saal 1", sitzplan);

        Film film2 = new Film("Once Upon a Time... in Hollywood", "Hier eine andere Beschreibung", "/img/2.jpg", "https://youtube.com/", 161, 16, 2, false, genre, sprachen);

        vorstellung1.setFilm(film2);
        Assert.assertEquals(film2, vorstellung1.getFilm());

        vorstellung1.setSaal(kinosaal2);
        Assert.assertEquals(kinosaal2, vorstellung1.getSaal());

        vorstellung1.setDatum(date2);
        Assert.assertEquals(date2, vorstellung1.getDatum());

        vorstellung1.setUhrzeit(time2);
        Assert.assertEquals(time2, vorstellung1.getUhrzeit());
    }
    //----

    // Tests for class Login
    @org.junit.Test
    public void testeLogin()
    {
        Login login = new Login();

        Assert.assertNotEquals(null, login);

        try{
            login = new Login("max.muster@mann.de", "e10adc3949ba59abbe56e057f20f883e");
        }

        catch (Exception e){
            Assert.assertEquals(1,0);
        }

        Assert.assertEquals(null, login.getLoginResult());
    }
    //----

    // TESTS FOR PASSWORD
    // Test for class PassMD5
    @org.junit.Test
    public void testePasswortHash()
    {
        // Passwort: 123456
        // MD5 Hash: e10adc3949ba59abbe56e057f20f883e

        String hash = "";

        try
        {
            hash = PassMD5.hash("123456");
        }
        
        catch (Exception e)
        {
            Assert.assertEquals(1,0);
        }

        Assert.assertEquals("e10adc3949ba59abbe56e057f20f883e", hash);
    }
    //----

    // TESTS FOR CONNECTOR
    @org.junit.Test
    public void testeConnector() throws Exception
    {

        //Assert.assertEquals(ClassNotFoundException, Connector.getConnection());

        ///???????????? WAS MACHE ICH HIER EIG??????????
        ResultSet resultSet = mock(ResultSet.class);
        PreparedStatement statement = mock(PreparedStatement.class);

        when(statement.getResultSet()).thenReturn(resultSet);

        when(resultSet.getString(eq("plz"))).thenReturn("85055");

        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);



        when(statement.execute()).thenReturn(true);


       /* Connection connection = Connector.getConnection();

        Assert.assertEquals(null, connection);

        Connector.closeConnection(connection);

        Assert.assertEquals(null, connection);*/
    }

    @org.junit.Test
    public void testeQueryBuilder() {

        //createLoginQuery
        Assert.assertEquals(
            "SELECT * FROM Person WHERE `E-Mail` = 'dieter@mail.com' AND `Passwort` = 'sicheresPasswort123' ;",
            QueryBuilder.createLoginQuery("dieter@mail.com", "sicheresPasswort123"));

        //createUser
        Assert.assertEquals(
            "INSERT INTO Person (`Vorname`, `Nachname`, `GebDatum`, `E-Mail`, `Passwort`) VALUES ('Max', 'Mustermann', '1990-08-07', 'max.mustermann@mail.com', 'passwort123'); "
                + "\n INSERT INTO Kunde (`PID`, `Treuepunkte`) VALUES ((SELECT `PID` FROM Person WHERE `Vorname` = 'Max' AND `Nachname` = 'Mustermann' AND `GebDatum` = '1990-08-07' AND `E-Mail` = 'max.mustermann@mail.com' AND `Passwort` = 'passwort123'), 0);",
            QueryBuilder.createUser("Max", "Mustermann", "1990-08-07", "max.mustermann@mail.com",
                "passwort123"));

        // ShowAllCinemas
        Assert.assertEquals("SELECT Ort.Ortsname, Ort.PLZ, `GebäudeId`, `Straße`, `Hausnummer` FROM Ort INNER JOIN Gebäude ON Ort.PLZ=Gebäude.PLZ ORDER BY `Ortsname`;", QueryBuilder.showAllCinemas());

        //showCinemaImaginationsToday
        Date date1 = new Date();
        String dateSQL1 = DateFormatter.getSQLDate(date1);
        String timeSQL1 = DateFormatter.getSQLTime(date1);
        Assert.assertEquals("SELECT * FROM Vorstellung WHERE `Datum` = '"+ dateSQL1 +"' AND `Uhrzeit` >= '"+timeSQL1+"';", QueryBuilder.showCinemaImaginationsToday());

        //showCinemaImaginationsThisWeek
        Date date2 = new Date();
        String dateSQL2 = DateFormatter.getSQLDate(date2);
        String timeSQL2 = DateFormatter.getSQLTime(date2);
        Assert.assertEquals("SELECT * FROM Vorstellung WHERE `Datum` >= '"+ dateSQL2 +"' AND `Uhrzeit` >= '"+timeSQL2+"';", QueryBuilder.showCinemaImaginationsThisWeek());

        //showTitlePageTitles
        Date date3 = new Date();
        String dateSQL3 = DateFormatter.getSQLDate(date3);
        Assert.assertEquals("SELECT DISTINCT Film.FilmID, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `BildLink` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID WHERE `Datum` >= '"+dateSQL3+"' LIMIT 3;", QueryBuilder.showTitlePageFilms());

        //showAllFilmInfos
        Assert.assertEquals("SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE `Titel` = 'Der Herr der Ringe'", QueryBuilder.showAllFilmInfos("Der Herr der Ringe"));

        //showGenresForFilmID
        Assert.assertEquals("SELECT `Genrebezeichnung`, `Deskriptor` FROM Genre, Film, FilmGenre WHERE FilmGenre.GenreID = Genre.GenreID AND Film.FilmID = FilmGenre.FilmID AND Film.FilmID = 1;", QueryBuilder.showGenresForFilmID(1));

        //showGeneresForFilmTitle
        Assert.assertEquals("SELECT `Genrebezeichnung`, `Deskriptor` FROM Genre, Film, FilmGenre WHERE FilmGenre.GenreID = Genre.GenreID AND Film.FilmID = FilmGenre.FilmID AND `Titel` = 'Der Herr der Ringe';", QueryBuilder.showGenresForFilmTitle("Der Herr der Ringe"));

        //showSearchResults
        Assert.assertEquals("SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE (`Titel` LIKE '%Der Herr der Ringe%' OR `Beschreibung` LIKE '%Der Herr der Ringe%') AND `Datum` >= '2019-08-07'AND `Uhrzeit` >= '19:30:00'AND `FSK` <= 12 ;",QueryBuilder.showSearchResults("Der Herr der Ringe", "2019-08-07", "19:30:00", 12));
        Assert.assertEquals("SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, `Sprachenname` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE `Datum` >= '2019-08-07'AND `Uhrzeit` >= '19:30:00'AND `FSK` <= 12 ;", QueryBuilder.showSearchResults("","2019-08-07","19:30:00", 12));

        //defaultSearchQuery
        Assert.assertEquals("SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `FSK`, `3D` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE (`Titel` LIKE '%Der Herr der Ringe%' OR `Beschreibung` LIKE '%Der Herr der Ringe%') AND `Datum` >= '2019-08-07' AND `Uhrzeit`>= '19:30:00' AND `FSK` <= 18 ;", QueryBuilder.defaultSearchQuery("Der Herr der Ringe", "2019-08-07", "19:30:00", 18));
        Assert.assertEquals("SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `FSK`, `3D` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE `Datum` >= '2019-08-07' AND `Uhrzeit`>= '19:30:00' AND `FSK` <= 18 ;", QueryBuilder.defaultSearchQuery("", "2019-08-07", "19:30:00", 18));

        //showMovieById
        Assert.assertEquals("SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, Sprache.Sprachenname, Kinosaal.SaalID FROM Vorstellung Join Film ON Vorstellung.FilmID = Film.FilmID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE Film.FilmID = '1' AND `Datum` >= '2019-08-07' AND `Uhrzeit` >= '19:30:00' AND Gebäude.PLZ = '68159' ORDER BY `Datum` ASC LIMIT 6; ", QueryBuilder.showMovieById("1","2019-08-07", "19:30:00", "68159"));

        //getGenreNamesById
        Assert.assertEquals("Select Genrebezeichnung FROM FilmGenre JOIN Genre ON FilmGenre.GenreID = Genre.GenreID Where FilmID = 1 ;", QueryBuilder.getGenreNamesById(1));

        //getSpracheById
       Assert.assertEquals("Select `Sprachenname` FROM Filmsprache JOIN Sprache ON Sprache.SprachID = Filmsprache.SprachID Where `FilmID` = 1 ;", QueryBuilder.getSpracheById(1));

        //getSaalById
        Assert.assertEquals("Select * From Kinosaal Where SaalID = 1 ;", QueryBuilder.getSaalById(1));

        //getMovieById
        Assert.assertEquals("Select * FROM Film Where FilmID = 1 ;", QueryBuilder.getMovieById(1));

        //getKinosByName
        Assert.assertEquals("SELECT `Straße`, `Hausnummer`, Gebäude.PLZ, `Ortsname` FROM Gebäude JOIN Ort ON Gebäude.PLZ = Ort.PLZ WHERE Ortsname = 'Berlin' ;",QueryBuilder.getKinosByName("Berlin"));
    }







    // TESTS FOR HANDLER

    @org.junit.Test
    public void testeCreateBookingHandler()
    {

    }

    @org.junit.Test
    public void testeDisplayMovieHandler()
    {

    }

    @org.junit.Test
    public void testeLoginHandler()
    {

    }

    @org.junit.Test
    public void testeRegistrationHandler() throws Exception
    {
        when(request4.getParameter("inputVorname")).thenReturn("Dietmar");
        when(request4.getParameter("inputNachname")).thenReturn("Hopp");
        when(request4.getParameter("inputGeb")).thenReturn("1979-05-05");
        when(request4.getParameter("inputEmailReg")).thenReturn("dietmar.hopp@mail.com");
        when(request4.getParameter("inputPasswordReg")).thenReturn("sicher123456");
        when(request4.getParameter("inputPasswordRegWdh")).thenReturn("sicher123456");

        when(mockConnection.createStatement()).thenReturn(mockStatement);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response4.getWriter()).thenReturn(printWriter);

        RegistrationHandler registrationHandler = new RegistrationHandler();
        registrationHandler.doPost(request4, response4);

        String result = stringWriter.getBuffer().toString();

        System.out.println(result);

        Assert.assertEquals("Geht nicht!", result);
    }

    @org.junit.Test
    public void testeSearchHandler()
    {

    }

    @org.junit.Test
    public void testeSingleMovieHandler() throws Exception
    {
        Cookie[] cookies = new Cookie[1];
        cookies[0] = new Cookie("plz", "86165");

        // Add mock parameters
        when(request6.getParameter("id")).thenReturn("1");
        when(request6.getParameter("date")).thenReturn("2019-01-01");
        when(request6.getParameter("time")).thenReturn("20:30:31");

        // Add mock cookies
        when(request6.getCookies()).thenReturn(cookies);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(response6.getWriter()).thenReturn(printWriter);

        SingleMovieHandler singleMovieHandler = new SingleMovieHandler();
        singleMovieHandler.doGet(request6, response6);

        String result = stringWriter.getBuffer().toString();

        System.out.println(result);
    }

    // TESTS FOR FACTORIES

    @org.junit.Test
    public void testeFilmFactory()
    {

    }

    @org.junit.Test
    public void testeKinosaalFactory()
    {

    }

    @org.junit.Test
    public void testeSupportMethods()
    {

    }

    @org.junit.Test
    public void testeVorstellungsFactory()
    {

    }

}
