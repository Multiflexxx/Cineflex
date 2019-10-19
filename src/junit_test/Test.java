package junit_test;

import Password.PassMD5;
import db_connector.Connector;
import db_connector.QueryBuilder;
import exception.UnequalParameterLength;
import factory.*;
import helper.*;
import oo.*;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {

    @InjectMocks
    Connector connector;
    @Mock
    Connection mockConnection;
    @Mock
    Statement mockStatement;
    @Mock
    private ResultSet resultSetMock1;
    @Mock
    private ResultSet resultSetMock2;
    @Mock
    private ResultSet resultSetMock3;
    @Mock
    private ResultSet resultSetMock4;
    @Mock
    private ResultSet resultSetMock5;
    @Mock
    private ResultSet resultSetMock6;
    @Mock
    private ResultSet resultSetMock7;
    @Mock
    private ResultSet resultSetMock8;
    @Mock
    private ResultSet resultSetMock9;
    @Mock
    private ResultSet resultSetMock10;
    @Mock
    private ResultSet resultSetMock11;
    @Mock
    private ResultSet resultSetMock12;
    @Mock
    private ResultSet resultSetMock13;
    @Mock
    private ResultSet resultSetMock14;
    @Mock
    private ResultSet resultSetMock15;
    @Mock
    private ResultSet resultSetMock16;
    @Mock
    private ResultSet resultSetMock17;
    @Mock
    private ResultSet resultSetMock18;
    @Mock
    private ResultSet resultSetMock19;
    @Mock
    private ResultSet resultSetMock20;
    @Mock
    private ResultSet resultSetMock21;
    @Mock
    private ResultSet resultSetMock22;
    @Mock
    private ResultSet resultSetMock23;
    @Mock
    private ResultSet resultSetMock24;

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
        Sitz sitz = new Sitz(2, 5, 'A', 'B');
        Assert.assertEquals(2, sitz.getSitzID());
        Assert.assertEquals(5, sitz.getNummer());
        Assert.assertEquals('A', sitz.getReihe());
        Assert.assertEquals('B', sitz.getSitzklasse());

        sitz.setSitzID(5);
        Assert.assertEquals(5, sitz.getSitzID());
        sitz.setNummer(42);
        Assert.assertEquals(42, sitz.getNummer());
        sitz.setReihe('F');
        Assert.assertEquals('F', sitz.getReihe());
        sitz.setSitzklasse('P');
        Assert.assertEquals('P', sitz.getSitzklasse());
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
    public void testeBuchungsbeleg() throws Exception
    {
        Sitz sitz1 = new Sitz(8, 7, 'C', 'L');
        Sitz sitz2 = new Sitz(9, 9, 'D', 'B');
        Sitz sitz3 = new Sitz(10, 10, 'D', 'P');
        Sitz sitz4 = new Sitz(11, 11, 'D', 'P');
        Sitz sitz5 = new Sitz(12, 12, 'D', 'P');

        Sitz[] sitzplan = {sitz1, sitz2, sitz3, sitz4, sitz5};

        // Check if seat was added to array and contains same values
        Assert.assertEquals(sitzplan[0], sitz1);

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

        date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-23");
        time = new SimpleDateFormat("HH:mm:ss").parse("19:30:00");

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

        // Format time using the DateFormatter class
        String buchungsZeit = DateFormatter.getSQLTime(vorstellung.getUhrzeit());

        // Check if formatting was successfully
        Assert.assertEquals("19:30:00", buchungsZeit);


        Date date2 = new Date();
        // Create new object (main reason for this test)

        Buchungsbeleg buchungsbeleg = new Buchungsbeleg(5, 16.51f, vorstellung, kunde, date2);

        // Check if class Buchungsbeleg works as expected
        Assert.assertEquals(5, buchungsbeleg.getBelegID());
        Assert.assertEquals(16.51f, buchungsbeleg.getPreis(),0);
        Assert.assertEquals(vorstellung, buchungsbeleg.getVorstellung());
        Assert.assertEquals(date2, buchungsbeleg.getUhrzeit());

        Assert.assertEquals(kunde, buchungsbeleg.getKunde());
        Assert.assertEquals(kunde.getTreuepunkte(), buchungsbeleg.getKunde().getTreuepunkte());

        // Set points for customer
        kunde.setTreuepunkte(25);
        buchungsbeleg.setKunde(kunde);
        Assert.assertEquals(25, buchungsbeleg.getKunde().getTreuepunkte());

        Assert.assertEquals(date2, buchungsbeleg.getUhrzeit());

        // Test setters
        buchungsbeleg.setBelegID(5);
        Assert.assertEquals(5, buchungsbeleg.getBelegID());

        buchungsbeleg.setPreis(5.69f);
        Assert.assertEquals(5.69f, buchungsbeleg.getPreis(),0);

        buchungsbeleg.setKunde(null);
        Assert.assertEquals(null, buchungsbeleg.getKunde());

        Date date3 = new Date();

        buchungsbeleg.setUhrzeit(date3);
        Assert.assertEquals(date3, buchungsbeleg.getUhrzeit());

        buchungsbeleg.setVorstellung(null);
        Assert.assertEquals(null, buchungsbeleg.getVorstellung());
    }

    // Tests for class Reservierungsbeleg
    @org.junit.Test
    public void testeReservierungsBeleg() throws Exception
    {
        Sitz sitz1 = new Sitz(1, 7, 'C', 'L');
        Sitz sitz2 = new Sitz(2, 9, 'D', 'B');
        Sitz sitz3 = new Sitz(3, 10, 'D', 'P');
        Sitz sitz4 = new Sitz(4, 11, 'D', 'P');
        Sitz sitz5 = new Sitz(5, 12, 'D', 'P');
        Sitz sitz6 = new Sitz(6, 12, 'D', 'P');


        Sitz[] sitzplan = {sitz1, sitz2, sitz3, sitz4, sitz5, sitz6};

        // Check if seat was added to array and contains same values
        Assert.assertEquals(sitzplan[1], sitz2);

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

        date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-24");
        time = new SimpleDateFormat("HH:mm:ss").parse("20:30:00");


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

        // Format time using the DateFormatter class
        String buchungsZeit = DateFormatter.getSQLTime(vorstellung.getUhrzeit());

        // Check if formatting was successfully
        Assert.assertEquals("20:30:00", buchungsZeit);

        Date date2 = new Date();

        // Create new object (main reason for this test)
        Reservierungsbeleg reservierungsbeleg = new Reservierungsbeleg(2, 10.98f, vorstellung, kunde, date2);

        // Check if class Buchungsbeleg works as expected
        Assert.assertEquals(2, reservierungsbeleg.getBelegID());
        Assert.assertEquals(10.98f, reservierungsbeleg.getPreis(),0);
        Assert.assertEquals(vorstellung, reservierungsbeleg.getVorstellung());
        Assert.assertEquals(date2, reservierungsbeleg.getUhrzeit());

        Assert.assertEquals(kunde, reservierungsbeleg.getKunde());
        Assert.assertEquals(kunde.getTreuepunkte(), reservierungsbeleg.getKunde().getTreuepunkte());

        // Set points for customer
        kunde.setTreuepunkte(30);
        reservierungsbeleg.setKunde(kunde);
        Assert.assertEquals(30, reservierungsbeleg.getKunde().getTreuepunkte());

        Assert.assertEquals(date2, reservierungsbeleg.getUhrzeit());

        // Test setters
        reservierungsbeleg.setBelegID(3);
        Assert.assertEquals(3, reservierungsbeleg.getBelegID());

        reservierungsbeleg.setPreis(7.69f);
        Assert.assertEquals(7.69f, reservierungsbeleg.getPreis(),0);

        reservierungsbeleg.setKunde(null);
        Assert.assertEquals(null, reservierungsbeleg.getKunde());

        Date date3 = new Date();

        reservierungsbeleg.setUhrzeit(date3);
        Assert.assertEquals(date3, reservierungsbeleg.getUhrzeit());

        reservierungsbeleg.setVorstellung(null);
        Assert.assertEquals(null, reservierungsbeleg.getVorstellung());
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

        //
        String[] sprachen3 = {"deutsch", "englisch", "türkisch"};
        String[] genre3 = {"Action", "Komödie"};
        Film film4 = new Film("König der Löwen 2", "Hier kommt die Beschreibung", "/img/1.jpg", "https://youtube.com/", 161, 12, 12, false, genre3, sprachen3);

        Assert.assertEquals("deutsch, englisch, türkisch", film4.getSpracheString());
        Assert.assertEquals("Action, Komödie", film4.getGenreString());
    }
    //----

    // Tests for class Kinosaal
    @org.junit.Test
    public void testeKinosaal()
    {
        Sitz sitz1 = new Sitz(2, 5, 'A', 'L');
        Sitz sitz2 = new Sitz(3, 6, 'A', 'L');
        Sitz sitz3 = new Sitz(5, 5, 'B', 'B');

        Sitz[] sitzplan = {sitz1, sitz2, sitz3};

        Kinosaal kinosaal = new Kinosaal(1, "Saal 1", sitzplan);

        Assert.assertEquals(1, kinosaal.getSaalID());
        Assert.assertEquals("Saal 1", kinosaal.getBezeichnung());
        Assert.assertEquals(sitzplan, kinosaal.getSitzplan());
        Assert.assertEquals(sitz1, kinosaal.getSitzplan()[0]);
        Assert.assertEquals(sitz2.getReihe(), kinosaal.getSitzplan()[0].getReihe());

        kinosaal.setSaalID(3);
        Assert.assertEquals(3, kinosaal.getSaalID());
        kinosaal.setBezeichnung("Saal 2");
        Assert.assertEquals("Saal 2", kinosaal.getBezeichnung());

        Sitz sitz4 = new Sitz(8, 7, 'C', 'L');
        Sitz sitz5 = new Sitz(9, 9, 'D', 'B');
        Sitz[] sitzplan2 = {sitz4, sitz5};
        kinosaal.setSitzplan(sitzplan2);

        Assert.assertEquals(sitzplan2, kinosaal.getSitzplan());

        Assert.assertEquals(1, kinosaal.getRowLength('C'));
    }
    //----

    // Tests for class Vorstellung
    @org.junit.Test
    public void testeVorstellung() throws Exception
    {
        Date date = null;
        Date time = null;
        Date date2 = null;
        Date time2 = null;

        date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-23");
        time = new SimpleDateFormat("HH:mm:ss").parse("19:30:00");
        date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-06");
        time2 = new SimpleDateFormat("HH:mm:ss").parse("15:30:00");

        Sitz sitz1 = new Sitz(2, 5, 'A', 'L');
        Sitz sitz2 = new Sitz(3, 6, 'A', 'L');
        Sitz sitz3 = new Sitz(5, 5, 'B', 'B');

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

        Sitz sitz4 = new Sitz(6, 5, 'A', 'L');
        Sitz sitz5 = new Sitz(7, 6, 'B', 'L');
        Sitz sitz6 = new Sitz(8, 5, 'B', 'B');

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

    // Tests for class Gebaeude
    @org.junit.Test
    public void testeGebaeude()
    {
        //public Gebäude(int gebäudeId, String strasse, int hausnummer, int plz, String ortsname) {

        Gebaeude gebäude = new Gebaeude(1, "Lange Straße", 3, 68165, "Mannheim");

        // Test Getter
        Assert.assertEquals(1, gebäude.getGebäudeId());
        Assert.assertEquals("Lange Straße", gebäude.getStrasse());
        Assert.assertEquals(3, gebäude.getHausnummer());
        Assert.assertEquals(68165, gebäude.getPlz());
        Assert.assertEquals("Mannheim", gebäude.getOrtsname());

        // Test Getter and Setter
        gebäude.setGebäudeId(5);
        Assert.assertEquals(5, gebäude.getGebäudeId());
        gebäude.setStrasse("Neue Straße");
        Assert.assertEquals("Neue Straße", gebäude.getStrasse());
        gebäude.setHausnummer(15);
        Assert.assertEquals(15, gebäude.getHausnummer());
        gebäude.setPlz(32839);
        Assert.assertEquals(32839, gebäude.getPlz());
        gebäude.setOrtsname("Steinheim");
        Assert.assertEquals("Steinheim", gebäude.getOrtsname());
    }
    //----

    // Tests for class Genre
    @org.junit.Test
    public void testeGenre()
    {
        Genre genre = new Genre(5, "Action", "Action Film");

        Assert.assertNotNull(genre);

        Assert.assertEquals(5, genre.getGenreID());
        Assert.assertEquals("Action", genre.getGenrebezeichnung());
        Assert.assertEquals("Action Film", genre.getDeskriptor());

        genre.setGenreID(15);
        Assert.assertEquals(15, genre.getGenreID());

        genre.setGenrebezeichnung("Horror");
        Assert.assertEquals("Horror", genre.getGenrebezeichnung());

        genre.setDeskriptor("Horror Film");
        Assert.assertEquals("Horror Film", genre.getDeskriptor());
    }
    //----

    // Tests for class UserLogin
    @org.junit.Test
    public void testeUserLogin()
    {
        String email = "mail@mail.com";
        String firstname = "Hans";
        String lastname = "Müller";
        int pid = 3;
        int kid = 15;

        // create new Object
        UserLogin userLogin = new UserLogin(email, firstname, lastname, pid, kid);

        Assert.assertNotNull(userLogin);

        // Test Getters
        Assert.assertEquals(email, userLogin.getEmail());
        Assert.assertEquals(firstname, userLogin.getFirstname());
        Assert.assertEquals(lastname, userLogin.getLastname());
        Assert.assertEquals(pid, userLogin.getPID());
        Assert.assertEquals(kid, userLogin.getKID());

        // Test Setters
        userLogin.setEmail("hans@mail.com");
        Assert.assertEquals("hans@mail.com", userLogin.getEmail());
        userLogin.setFirstname("Franz");
        Assert.assertEquals("Franz", userLogin.getFirstname());
        userLogin.setLastname("Meier");
        Assert.assertEquals("Meier", userLogin.getLastname());
        userLogin.setPID(78);
        Assert.assertEquals(78, userLogin.getPID());
        userLogin.setKID(12);
        Assert.assertEquals(12, userLogin.getKID());
    }
    //----

    // Tests for class Sitzsperre

    @org.junit.Test
    public void testeSitzsperre()
    {
        Date date = new Date();

        Sitzsperre sitzsperre = new Sitzsperre(1, 5,7, date);

        Assert.assertEquals(1, sitzsperre.getSitzplatzID());
        //Assert.assertEquals(5, sitzsperre.getBNR());
        Assert.assertEquals(7, sitzsperre.getKNR());
        Assert.assertEquals(date, sitzsperre.getTimestamp());

        sitzsperre.setSitzplatzID(2);
        //sitzsperre.setBNR(6);
        sitzsperre.setKNR(8);

        Date date2 = new Date();
        sitzsperre.setTimestamp(date2);

        Assert.assertEquals(2, sitzsperre.getSitzplatzID());
        //Assert.assertEquals(6, sitzsperre.getBNR());
        Assert.assertEquals(8, sitzsperre.getKNR());

        Assert.assertEquals(date2, sitzsperre.getTimestamp());
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

    @org.junit.Test
    public void testeQueryBuilder() {

        //createLoginQuery
        Assert.assertEquals(
            "Select person.PID as PID, Vorname, Nachname, GebDatum, `E-Mail`, KID, Treuepunkte From person Join kunde k on person.PID = k.PID Where `E-Mail` = 'dieter@mail.com' AND Passwort = 'sicheresPasswort123';",
            QueryBuilder.createLoginQuery("dieter@mail.com", "sicheresPasswort123"));

        //createUser
       /* Assert.assertEquals(
            "INSERT INTO Person (`Vorname`, `Nachname`, `GebDatum`, `E-Mail`, `Passwort`) VALUES ('Max', 'Mustermann', '1990-08-07', 'max.mustermann@mail.com', 'passwort123'); "
                + "\n INSERT INTO Kunde (`PID`, `Treuepunkte`) VALUES ((SELECT `PID` FROM Person WHERE `Vorname` = 'Max' AND `Nachname` = 'Mustermann' AND `GebDatum` = '1990-08-07' AND `E-Mail` = 'max.mustermann@mail.com' AND `Passwort` = 'passwort123'), 0);",
            QueryBuilder.createUser("Max", "Mustermann", "1990-08-07", "max.mustermann@mail.com",
                "passwort123"));*/

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

        //TODO: Change concat with Date and Time

        // defaultSearchQuery with Search Text
        Assert.assertEquals("SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID WHERE (`Titel` LIKE '%Toy%' OR `Beschreibung` LIKE '%Toy%') AND `Datum` >= '2019-08-08' AND `Uhrzeit`>= '20:00:00' AND Gebäude.PLZ = '68165' AND `FSK` <= 18 ;", QueryBuilder.defaultSearchQuery("Toy", "2019-08-08", "20:00:00", 18, "68165"));
        // defaultSearchQuery without Search Text
        Assert.assertEquals("SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID WHERE `Datum` >= '2019-08-08' AND `Uhrzeit`>= '20:00:00' AND Gebäude.PLZ = '68165' AND `FSK` <= 18 ;", QueryBuilder.defaultSearchQuery("", "2019-08-08", "20:00:00", 18, "68165"));

        //showMovieById
        Assert.assertEquals("SELECT `VorstellungsID`, `Datum`, `Uhrzeit`, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `3D`, `BildLink`, `TrailerLink`, Sprache.Sprachenname, Kinosaal.SaalID FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID WHERE Film.FilmID = '1' AND concat(`Datum`,  ' ', `Uhrzeit`) >= '2019-08-07 19:30:00' AND Gebäude.PLZ = '68159' ORDER BY `Datum` ASC LIMIT 6;", QueryBuilder.showMovieById("1","2019-08-07", "19:30:00", "68159"));

        //getSaalById
        Assert.assertEquals("Select * From Kinosaal Where SaalID = 1 ;", QueryBuilder.getSaalById(1));

        //getMovieById
        Assert.assertEquals("Select * FROM Film Where FilmID = 1 ;", QueryBuilder.getMovieById(1));

        //getKinosByName
        Assert.assertEquals("SELECT `Straße`, `Hausnummer`, Gebäude.PLZ, `Ortsname` FROM Gebäude JOIN Ort ON Gebäude.PLZ = Ort.PLZ WHERE Ortsname = 'Berlin' ;",QueryBuilder.getKinosByName("Berlin"));

        //getGenreNamesById(int id)
        Assert.assertEquals("Select `Genrebezeichnung` FROM FilmGenre JOIN Genre ON FilmGenre.GenreID = Genre.GenreID Where FilmID = 2;", QueryBuilder.getGenreNamesById(2));

        //getSpracheById(int id)
        Assert.assertEquals("Select `Sprachenname` FROM Filmsprache JOIN Sprache ON Sprache.SprachID = Filmsprache.SprachID Where `FilmID` = 5;", QueryBuilder.getSpracheById(5));

        //getKinosByName(String stadt)
        Assert.assertEquals("SELECT `Straße`, `Hausnummer`, Gebäude.PLZ, `Ortsname` FROM Gebäude JOIN Ort ON Gebäude.PLZ = Ort.PLZ WHERE Ortsname = 'Mannheim' ;", QueryBuilder.getKinosByName("Mannheim"));

        //getSaalById(int id)
        Assert.assertEquals("Select * From Kinosaal Where SaalID = 10 ;", QueryBuilder.getSaalById(10));

        //getSitzplanBySaalID(int id)
        Assert.assertEquals("SELECT Kinosaal.SaalID as SaalID, GebäudeID, Saalbezeichnung, Sitzplan.SitzplanID as SitzplanID, SitzplatzID, Reihe, Nummer, Sitzklasse FROM Cineflex.Kinosaal JOIN Cineflex.Sitzplan ON Sitzplan.SaalID = Kinosaal.SaalID JOIN Cineflex.Sitz ON Sitzplan.SitzplanID = Sitz.SitzplanID WHERE Kinosaal.SaalID = 5;", QueryBuilder.getSitzplanBySaalID(5));

        //showTitelPageFilmsbyPLZ
        Date date4 = new Date();
        String dateSQL4 = DateFormatter.getSQLDate(date4);
        Assert.assertEquals("SELECT DISTINCT Film.FilmID, `Titel`, `Beschreibung`, `Dauer`, `FSK`, `BildLink`, `TrailerLink`, `3D` FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID WHERE `Datum` >= '" + dateSQL4 + "' AND Gebäude.PLZ = '68165' LIMIT 3;", QueryBuilder.showTitelPageFilmsbyPLZ("68165"));

        //getVorstellungByID(int id)
        Assert.assertEquals("SELECT VorstellungsID, Datum, Uhrzeit, Film.FilmID as FilmID, Vorstellung.SaalID as SaalID, Sprache.SprachID as SprachID, Titel, Beschreibung, Dauer, FSK, 3D, BildLink, TrailerLink, Grundpreis, Sprachenname, GebäudeID, Saalbezeichnung FROM Cineflex.Vorstellung JOIN Cineflex.Film ON Vorstellung.FilmID = Film.FilmID JOIN Cineflex.Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Cineflex.Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID WHERE VorstellungsID = 5 ;", QueryBuilder.getVorstellungByID(5));

        //getGenreByID
        Assert.assertEquals("Select `Genrebezeichnung` FROM FilmGenre JOIN Genre ON Genre.GenreID = Filmgenre.GenreID Where `GenreID` = 15 ;", QueryBuilder.getGenreByID(15));

        //createUser
        Assert.assertEquals("INSERT INTO Person (`Vorname`, `Nachname`, `GebDatum`, `E-Mail`, `Passwort`, `Hausnummer`, `Straße`, `Adresszusatz`) VALUES ('Hans', 'Meier', '01.01.1970', 'mail@mail.com', 'hashCode', '15', 'Langer Weg', ''); \n INSERT INTO Kunde (`PID`, `Treuepunkte`) VALUES ((SELECT `PID` FROM Person WHERE `Vorname` = 'Hans' AND `Nachname` = 'Meier' AND `GebDatum` = '01.01.1970' AND `E-Mail` = 'mail@mail.com' AND `Passwort` = 'hashCode'), 0);", QueryBuilder.createUser("Hans", "Meier", "01.01.1970", "mail@mail.com", "hashCode", "15","Langer Weg", ""));

        //getGenres
        Assert.assertEquals("SELECT DISTINCT `GenreID`, `Genrebezeichnung`, `Deskriptor` FROM Genre;", QueryBuilder.getGenres());

        //getGrundPreis
        Assert.assertEquals("SELECT `Grundpreis`, `Dauer`, `3D`FROM Film WHERE `FilmId` =20;", QueryBuilder.getGrundPreis(20));

        //getPreisveränderungen
        Assert.assertEquals("SELECT * FROM Preisänderung WHERE `PreisänderungsID` = 4 OR `PreisänderungsID` = 5;", QueryBuilder.getPreisveränderungen());

        //getPreiseInfos
        Assert.assertEquals("SELECT * FROM Preisänderung WHERE Änderungsbeschreibung != 'Logenaufpreis' AND Änderungsbeschreibung != '3D-Aufschlag' AND Änderungsbeschreibung != 'Überlängenaufschlag';", QueryBuilder.getPreiseInfos());

        //getVorstellungByIdPLZ()
        Assert.assertEquals("SELECT VorstellungsID, Datum, Uhrzeit, Film.FilmID as FilmID, Vorstellung.SaalID as SaalID, Sprache.SprachID as SprachID, Titel, Beschreibung, Dauer, FSK, 3D, BildLink, TrailerLink, Grundpreis, Sprachenname, Kinosaal.GebäudeID, Saalbezeichnung, PLZ FROM Cineflex.Vorstellung JOIN Cineflex.Film ON Vorstellung.FilmID = Film.FilmID JOIN Cineflex.Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Cineflex.Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID Join Cineflex.Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID WHERE VorstellungsID = 68165;", QueryBuilder.getVorstellungByIdPLZ(68165));

        //getSitzById()
        Assert.assertEquals("Select * From sitz Where SitzplatzID = 20;", QueryBuilder.getSitzById(20));

        //createBuchungsBeleg()
        Assert.assertEquals("Insert INTO Buchungsbeleg (BNR, KID, VorstellungsID, Preis, Zeitstempel) VALUES (NULL, 15, 20, 25.25, '2019-08-08 12:30:33');", QueryBuilder.createBuchungsBeleg(15, 20, 25.25f, "2019-08-08 12:30:33"));

        //createBuchungsposition
        Assert.assertEquals("Insert INTO Buchungsposition (PositionsID, BNR, SitzID) VALUES ( 5, 15, 12);", QueryBuilder.createBuchungsposition(5, 15, 12));

        //createPreisänderungBuchung
        Assert.assertEquals("Insert Into PreisänderungBuchung (PositionsID, BNR, PreisänderungsID) Values ( 4, 8, 3) ;", QueryBuilder.createPreisänderungBuchung(4, 8, 3));

        //genreSearchQuery()
        Assert.assertEquals("SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D`, Genre.GenreID FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID JOIN FilmGenre ON Film.FilmID = FilmGenre.FilmID JOIN Genre On Genre.GenreID = FilmGenre.GenreID WHERE (`Titel` LIKE '%Toy%' OR `Beschreibung` LIKE '%Toy%') AND `Datum` >= '2019-08-08' AND `Uhrzeit`>= '16:30:00' AND Gebäude.PLZ = '68165' AND `FSK` <= 16 AND Genre.GenreID = 5 ;", QueryBuilder.genreSearchQuery("Toy", "2019-08-08", "16:30:00", 16, "68165", 5));
        Assert.assertEquals("SELECT DISTINCT Vorstellung.FilmID, `Titel`, `Dauer`, `FSK`, `BildLink`, Film.Beschreibung, `TrailerLink`, `3D`, Genre.GenreID FROM Vorstellung JOIN Film ON Vorstellung.FilmID = Film.FilmID JOIN Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID JOIN Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID JOIN FilmGenre ON Film.FilmID = FilmGenre.FilmID JOIN Genre On Genre.GenreID = FilmGenre.GenreID WHERE `Datum` >= '2019-08-08' AND `Uhrzeit`>= '16:30:00' AND Gebäude.PLZ = '68165' AND `FSK` <= 16 AND Genre.GenreID = 5 ;", QueryBuilder.genreSearchQuery("", "2019-08-08", "16:30:00", 16, "68165", 5));
    }

    // TESTS FOR HELPERS

    // Tests for class DateFormatter
    @org.junit.Test
    public void testeDateFormatter()
    {
        Date date = new Date();

        SimpleDateFormat frontendDate = new SimpleDateFormat("EEE, dd. MMM", new Locale("de", "DE"));
        SimpleDateFormat frontendTime = new SimpleDateFormat("HH:mm", new Locale("de", "DE"));
        SimpleDateFormat sqlDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sqlTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sqlDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Assert.assertEquals(frontendDate.format(date), DateFormatter.getFrontendDate(date));
        Assert.assertEquals(frontendTime.format(date), DateFormatter.getFrontendTime(date));
        Assert.assertEquals(sqlDate.format(date), DateFormatter.getSQLDate(date));
        Assert.assertEquals(sqlTime.format(date), DateFormatter.getSQLTime(date));

        Assert.assertEquals(sqlDateTime.format(date), DateFormatter.getSQLDateAndTime(date));
    }

    // Tests for class SupportMethods
    @org.junit.Test
    public void testeSupportMethods() throws Exception
    {
        // Create Resultset
        resultSetMock2 = Mockito.mock(ResultSet.class);

        //Example Resultset Query:
        //SELECT `Straße`, `Hausnummer`, Gebäude.PLZ, `Ortsname` FROM Gebäude JOIN Ort ON Gebäude.PLZ = Ort.PLZ WHERE Ortsname = '"+ stadt +"' ;";

        Mockito.when(resultSetMock2.getString("Straße")).thenReturn("Lange Straße");
        Mockito.when(resultSetMock2.getString("Hausnummer")).thenReturn("1");
        Mockito.when(resultSetMock2.getString("PLZ")).thenReturn("68165");
        Mockito.when(resultSetMock2.getString("Ortsname")).thenReturn("Mannheim");

        // Add next() to ResultSet
        Mockito.when(resultSetMock2.next()).thenReturn(true).thenReturn(false);

        // Create Support Methods Object

        // call get ResultSetSize()
        int size = SupportMethods.getResultSetSize(resultSetMock2);

        Assert.assertEquals(1, size);

        int size2 = SupportMethods.getResultSetSize(null);

        Assert.assertEquals(-1, size2);

        // Remove HTML Tags from String

        String html = "<html>Test</html>#123+?Test.|A BC";
        String checkString = "htmlTest/html123TestA BC";

        Assert.assertEquals(checkString, SupportMethods.removeHTMLCode(html));
    }

    // Tests for class Exception Handler
    @org.junit.Test
    public void testeExeptionHandler()
    {
        IllegalArgumentException exception = new IllegalArgumentException("Argument 'divisor' is 0");

        // Exception Object Exists
        Assert.assertNotNull(exception);

        // Assert that String length is > 0, so String is build
        Assert.assertTrue(ExceptionHandler.exceptionStackTraceToString(exception).length() > 0);
    }

    // Tests for class SeatIDFormatter
    @org.junit.Test
    public void testeSeatIDFormatter()
    {
        String array = "-1|2|-1|4|-1|-1|-1|8";

        int[] checkArray = {2,4,8};
        int[] intArray = SeatIDFormatter.seatsStringToIntArray(array);

        Assert.assertEquals(checkArray[0], intArray[0]);
        Assert.assertEquals(checkArray[1], intArray[1]);
        Assert.assertEquals(checkArray[2], intArray[2]);

        Assert.assertEquals(null, SeatIDFormatter.seatsStringToIntArray("-1|-1"));
    }

    @org.junit.Test
    public void testeArrayBuilder()
    {
        String inputString = "5, 8, 9";
        int[] checkArray = {5, 8, 9};
        int[] array = {6, 7, 8};

        Assert.assertEquals(checkArray[0], ArrayBuilder.stringToIntArray(inputString, ", ")[0]);
        Assert.assertEquals(checkArray[1], ArrayBuilder.stringToIntArray(inputString, ", ")[1]);
        Assert.assertEquals(checkArray[2], ArrayBuilder.stringToIntArray(inputString, ", ")[2]);


        Assert.assertEquals("6, 7, 8", ArrayBuilder.intArrayToString(array));

        Assert.assertEquals("6< 7< 8", ArrayBuilder.intArrayToString(array, "< "));
    }

    @org.junit.Test
    public void testePLZFormatter()
    {
        int plz1 = 1;
        int plz2 = 12;
        int plz3 = 123;
        int plz4 = 1234;
        int plz5 = 12345;

        String out = PLZFormatter.addLeadingZeros(plz1);
        Assert.assertEquals("00001", out);
        out = PLZFormatter.addLeadingZeros(plz2);
        Assert.assertEquals("00012", out);
        out = PLZFormatter.addLeadingZeros(plz3);
        Assert.assertEquals("00123", out);
        out = PLZFormatter.addLeadingZeros(plz4);
        Assert.assertEquals("01234", out);
        out = PLZFormatter.addLeadingZeros(plz5);
        Assert.assertEquals("12345", out);
    }


    //TODO: ÄNDERN

    // TESTS FOR CONNECTOR
    @org.junit.Test
    public void testeConnector() throws Exception
    {

    }

    // TESTS FOR FACTORIES

    @org.junit.Test
    public void testeGebaeudeFactory() throws Exception
    {
        // Create Resultset
        resultSetMock4 = Mockito.mock(ResultSet.class);

        // Query used by GebauedeFactory:
        // SELECT Ort.Ortsname, Ort.PLZ, `GebäudeId`, `Straße`, `Hausnummer` FROM Ort INNER JOIN Gebäude ON Ort.PLZ=Gebäude.PLZ ORDER BY `Ortsname`;

        // Add values to Resultset
        Mockito.when(resultSetMock4.getInt("GebäudeId")).thenReturn(1).thenReturn(2).thenReturn(3);
        Mockito.when(resultSetMock4.getString("Straße")).thenReturn("Test Straße").thenReturn("Kurze Test Straße").thenReturn("Lange Test Straße");
        Mockito.when(resultSetMock4.getInt("Hausnummer")).thenReturn(4).thenReturn(5).thenReturn(6);
        Mockito.when(resultSetMock4.getInt("PLZ")).thenReturn(32839).thenReturn(68165).thenReturn(69115);
        Mockito.when(resultSetMock4.getString("Ort.Ortsname")).thenReturn("Steinheim").thenReturn("Mannheim").thenReturn("Heidelberg");

        // Add next() to ResultSet
        Mockito.when(resultSetMock4.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

        Gebaeude[] resultGebaeudeArray = GebaeudeFactory.getGebaeude(resultSetMock4);

        // Check if result is not null
        Assert.assertNotNull(resultGebaeudeArray);

        Assert.assertEquals("Steinheim", resultGebaeudeArray[0].getOrtsname());
        Assert.assertEquals("Mannheim", resultGebaeudeArray[1].getOrtsname());
        Assert.assertEquals("Heidelberg", resultGebaeudeArray[2].getOrtsname());

        Assert.assertEquals(32839, resultGebaeudeArray[0].getPlz());
        Assert.assertEquals(68165, resultGebaeudeArray[1].getPlz());
        Assert.assertEquals(69115, resultGebaeudeArray[2].getPlz());

        Assert.assertEquals(1, resultGebaeudeArray[0].getGebäudeId());
        Assert.assertEquals(2, resultGebaeudeArray[1].getGebäudeId());
        Assert.assertEquals(3, resultGebaeudeArray[2].getGebäudeId());

        Assert.assertEquals("Test Straße", resultGebaeudeArray[0].getStrasse());
        Assert.assertEquals("Kurze Test Straße", resultGebaeudeArray[1].getStrasse());
        Assert.assertEquals("Lange Test Straße", resultGebaeudeArray[2].getStrasse());

        Assert.assertEquals(4, resultGebaeudeArray[0].getHausnummer());
        Assert.assertEquals(5, resultGebaeudeArray[1].getHausnummer());
        Assert.assertEquals(6, resultGebaeudeArray[2].getHausnummer());
    }

    @org.junit.Test
    public void testeSitzFactory() throws Exception
    {
        // Create Resultset
        resultSetMock3 = Mockito.mock(ResultSet.class);

        // Query used for SitzFactory:
        // Select * From sitz Where SitzplatzID = " + id + ";

        // Add values to Resultset
        Mockito.when(resultSetMock3.getInt("SitzplatzID")).thenReturn(10).thenReturn(11);
        Mockito.when(resultSetMock3.getInt("Nummer")).thenReturn(2).thenReturn(3);
        Mockito.when(resultSetMock3.getString("Reihe")).thenReturn("B").thenReturn("C");
        Mockito.when(resultSetMock3.getString("Sitzklasse")).thenReturn("L").thenReturn("P");

        // Add next() to ResultSet
        Mockito.when(resultSetMock3.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        Sitz resultSeat = SitzFactory.getSitzById(10, resultSetMock3);

        // Test if ResultSeat is not Null
        Assert.assertNotNull(resultSeat);

        Assert.assertEquals(10, resultSeat.getSitzID());
        Assert.assertEquals(2, resultSeat.getNummer());
        Assert.assertEquals('B', resultSeat.getReihe());
        Assert.assertEquals('L', resultSeat.getSitzklasse());
    }

    @org.junit.Test
    public void testeAnfahrtseiteFactory() throws Exception
    {
        // Create Resultset
        resultSetMock1 = Mockito.mock(ResultSet.class);

        //Resultset Query used for DB:
        //SELECT `Straße`, `Hausnummer`, Gebäude.PLZ, `Ortsname` FROM Gebäude JOIN Ort ON Gebäude.PLZ = Ort.PLZ WHERE Ortsname = '"+ stadt +"' ;"

        // Add values to Resultset
        Mockito.when(resultSetMock1.getString("Straße")).thenReturn("Kurze Straße");
        Mockito.when(resultSetMock1.getString("Hausnummer")).thenReturn("2");
        Mockito.when(resultSetMock1.getString("PLZ")).thenReturn("32839");
        Mockito.when(resultSetMock1.getString("Ortsname")).thenReturn("Steinheim");

        // Add next() to ResultSet
        Mockito.when(resultSetMock1.next()).thenReturn(true).thenReturn(false);

        // call get ResultSetSize()
        int size = SupportMethods.getResultSetSize(resultSetMock1);

        // check Size of Mock Resultset
        Assert.assertEquals(1, size);

        String checkString1 = "<iframe src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyCM1EeAy6KlTOa-jHIsL_rCEhDghnqZ5Y8&q=Kurze+Stra%C3%9Fe+2+32839\" width=\"1000\" height=\"1000\"></iframe>";

        //Check return
        Assert.assertEquals(checkString1, AnfahrtsseiteFactory.getAnfahrtsseite("Steinheim", resultSetMock1));
    }

    @org.junit.Test
    public void testeLoginFactory() throws Exception
    {
        // Create Resultset
        resultSetMock5 = Mockito.mock(ResultSet.class);

        //Resultset Query used for DB:
        // Select person.PID as PID, Vorname, Nachname, GebDatum, `E-Mail`, KID, Treuepunkte From person Join kunde k on person.PID = k.PID Where `E-Mail` = '"  + email + "' AND Passwort = '" + passwordHash + "';"

        // Add values to Resultset
        Mockito.when(resultSetMock5.getString("E-Mail")).thenReturn("mail@mail.com");
        Mockito.when(resultSetMock5.getString("Vorname")).thenReturn("Peter");
        Mockito.when(resultSetMock5.getString("Nachname")).thenReturn("Meier");
        Mockito.when(resultSetMock5.getString("Passwort")).thenReturn("hash");
        Mockito.when(resultSetMock5.getInt("PID")).thenReturn(6);
        Mockito.when(resultSetMock5.getInt("KID")).thenReturn(8);

        // Add next() to ResultSet
        Mockito.when(resultSetMock5.next()).thenReturn(true).thenReturn(false);

        UserLogin resultUserLogin = LoginFactory.getUserLogin("mail@mail.com", "hash", resultSetMock5);

        Assert.assertNotNull(resultUserLogin);

        Assert.assertEquals("mail@mail.com", resultUserLogin.getEmail());
        Assert.assertEquals("Peter", resultUserLogin.getFirstname());
        Assert.assertEquals("Meier", resultUserLogin.getLastname());
        Assert.assertEquals(6, resultUserLogin.getPID());
        Assert.assertEquals(8, resultUserLogin.getKID());
    }

    @org.junit.Test
    public void testeUpdateFactory() throws Exception
    {
        // Create Resultset
        resultSetMock6 = Mockito.mock(ResultSet.class);

        //Resultset Query used for DB:
        //SELECT VorstellungsID, Datum, Uhrzeit, Film.FilmID as FilmID, Vorstellung.SaalID as SaalID, Sprache.SprachID as SprachID, Titel, Beschreibung, Dauer, FSK, 3D, BildLink, TrailerLink, Grundpreis, Sprachenname, Kinosaal.GebäudeID, Saalbezeichnung, PLZ FROM Cineflex.Vorstellung JOIN Cineflex.Film ON Vorstellung.FilmID = Film.FilmID JOIN Cineflex.Sprache ON Vorstellung.SprachID = Sprache.SprachID JOIN Cineflex.Kinosaal ON Vorstellung.SaalID = Kinosaal.SaalID Join Cineflex.Gebäude ON Kinosaal.GebäudeID = Gebäude.GebäudeID WHERE VorstellungsID = " + id + ";"

        // Add values to Resultset
        Mockito.when(resultSetMock6.getInt("PLZ")).thenReturn(68165).thenReturn(32839);

        // Add next() to ResultSet
        Mockito.when(resultSetMock6.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        // Create Object of class Vorstellung
        Vorstellung vorstellung = new Vorstellung(10, null, null, "deutsch", null, null);

        // Vorstellung is available for PLZ
        boolean resultBoolean1 = UpdateFactory.checkVorstellungPLZ(vorstellung, 68165, resultSetMock6);
        Assert.assertEquals(true, resultBoolean1);

        // Vorstellung is unavailable for PLZ
        boolean resultBoolean2 = UpdateFactory.checkVorstellungPLZ(vorstellung, 12345, resultSetMock6);
        Assert.assertEquals(false, resultBoolean2);
    }

    @org.junit.Test
    public void testeGenreFactory() throws Exception
    {
        // Create Resultset
        resultSetMock7 = Mockito.mock(ResultSet.class);

        // Add values to Resultset
        Mockito.when(resultSetMock7.getInt("GenreID")).thenReturn(5);
        Mockito.when(resultSetMock7.getString("Genrebezeichnung")).thenReturn("Action");
        Mockito.when(resultSetMock7.getString("Genrebeschreibung")).thenReturn("Action Film");

        // Add next() to ResultSet
        Mockito.when(resultSetMock7.next()).thenReturn(true).thenReturn(false);

        // Test getGenreById
        Genre resultGenreId = GenreFactory.getGenreById(5, resultSetMock7);

        // Check if Object is not null
        Assert.assertNotNull(resultGenreId);

        Assert.assertEquals("Action", resultGenreId.getGenrebezeichnung());
        Assert.assertEquals("Action Film", resultGenreId.getDeskriptor());


        // Create Resultset
        resultSetMock8 = Mockito.mock(ResultSet.class);

        // Add values to Resultset
        Mockito.when(resultSetMock8.getInt("GenreID")).thenReturn(5).thenReturn(15).thenReturn(20).thenReturn(30);
        Mockito.when(resultSetMock8.getString("Genrebezeichnung")).thenReturn("Action").thenReturn("Drama").thenReturn("Komödie").thenReturn("Horror");
        Mockito.when(resultSetMock8.getString("Deskriptor")).thenReturn("Action Film").thenReturn("Drama Film").thenReturn("Lustiger Film").thenReturn("Horror Film");

        // Add next() to ResultSet
        Mockito.when(resultSetMock8.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);

        // Test getGenre
        Genre[] resultGenres = GenreFactory.getGenre(resultSetMock8);

        // Check if Array is not null
        Assert.assertNotNull(resultGenres);

        Assert.assertEquals(5, resultGenres[0].getGenreID());
        Assert.assertEquals(15, resultGenres[1].getGenreID());
        Assert.assertEquals(20, resultGenres[2].getGenreID());
        Assert.assertEquals(30, resultGenres[3].getGenreID());

        Assert.assertEquals("Action", resultGenres[0].getGenrebezeichnung());
        Assert.assertEquals("Drama", resultGenres[1].getGenrebezeichnung());
        Assert.assertEquals("Komödie", resultGenres[2].getGenrebezeichnung());
        Assert.assertEquals("Horror", resultGenres[3].getGenrebezeichnung());

        Assert.assertEquals("Action Film", resultGenres[0].getDeskriptor());
        Assert.assertEquals("Drama Film", resultGenres[1].getDeskriptor());
        Assert.assertEquals("Lustiger Film", resultGenres[2].getDeskriptor());
        Assert.assertEquals("Horror Film", resultGenres[3].getDeskriptor());
    }

    @org.junit.Test
    public void testeKinosaalFactory() throws Exception
    {
        // Create Resultset
        resultSetMock9 = Mockito.mock(ResultSet.class);
        resultSetMock10 = Mockito.mock(ResultSet.class);

        // Add values to Resultset
        Mockito.when(resultSetMock9.getInt("SitzplatzID")).thenReturn(5).thenReturn(6).thenReturn(7);
        Mockito.when(resultSetMock9.getInt("Nummer")).thenReturn(4).thenReturn(5).thenReturn(6);
        Mockito.when(resultSetMock9.getString("Reihe")).thenReturn("A").thenReturn("B").thenReturn("C");
        Mockito.when(resultSetMock9.getString("Sitzklasse")).thenReturn("L").thenReturn("P").thenReturn("B");

        Mockito.when(resultSetMock10.getString("Saalbezeichnung")).thenReturn("Saal 1");

        // Add next() to ResultSet
        Mockito.when(resultSetMock9.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock10.next()).thenReturn(true).thenReturn(false);

        // Create Kinosaal Object via Factory
        Kinosaal resultKinosaal = KinosaalFactory.getKinosaal(15, resultSetMock9, resultSetMock10);

        // Check if Object is not null
        Assert.assertNotNull(resultKinosaal);

        // Test if Sitzplan array is filled correctly
        Assert.assertEquals(15, resultKinosaal.getSaalID());
        Assert.assertEquals("Saal 1", resultKinosaal.getBezeichnung());
        Assert.assertEquals(6, resultKinosaal.getSitzplan()[1].getSitzID());
        Assert.assertEquals('A', resultKinosaal.getSitzplan()[0].getReihe());
    }

    @org.junit.Test
    public void testeKundenFactory() throws Exception
    {
        // Create Resultset
        resultSetMock11 = Mockito.mock(ResultSet.class);
        resultSetMock12 = Mockito.mock(ResultSet.class);

        // Add values to Resultset
        Mockito.when(resultSetMock11.getString("E-Mail")).thenReturn("mail@mail.com");
        Mockito.when(resultSetMock11.getString("Vorname")).thenReturn("Hans");
        Mockito.when(resultSetMock11.getString("Nachname")).thenReturn("Müller");
        Mockito.when(resultSetMock11.getString("Passwort")).thenReturn("hash");
        Mockito.when(resultSetMock11.getString("GebDatum")).thenReturn("1970-01-01");
        Mockito.when(resultSetMock11.getInt("PID")).thenReturn(4);
        Mockito.when(resultSetMock11.getString("Straße")).thenReturn("Test Weg");
        Mockito.when(resultSetMock11.getInt("Hausnummer")).thenReturn(7);
        Mockito.when(resultSetMock11.getInt("KID")).thenReturn(13);
        Mockito.when(resultSetMock11.getInt("Treuepunkte")).thenReturn(35);

        Mockito.when(resultSetMock12.getString("E-Mail")).thenReturn("mail@mail.com");
        Mockito.when(resultSetMock12.getString("Vorname")).thenReturn("Peter");
        Mockito.when(resultSetMock12.getString("Nachname")).thenReturn("Huber");
        Mockito.when(resultSetMock12.getString("Passwort")).thenReturn("hash");
        Mockito.when(resultSetMock12.getString("GebDatum")).thenReturn("1970-01-01");
        Mockito.when(resultSetMock12.getInt("PID")).thenReturn(4);
        Mockito.when(resultSetMock12.getString("Straße")).thenReturn("Test Weg");
        Mockito.when(resultSetMock12.getInt("Hausnummer")).thenReturn(7);
        Mockito.when(resultSetMock12.getInt("KID")).thenReturn(17);
        Mockito.when(resultSetMock12.getInt("Treuepunkte")).thenReturn(40);

        // Add next() to ResultSet
        Mockito.when(resultSetMock11.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock12.next()).thenReturn(true).thenReturn(false);

        // Create Object via Factory
        Kunde resultKunde1 = KundenFactory.getKunde(4, resultSetMock11);
        Kunde resultKunde2 = KundenFactory.getKundeByKID(17, resultSetMock12);

        // Check if Objects are not null
        Assert.assertNotNull(resultKunde1);
        Assert.assertNotNull(resultKunde1);

        // Check Customer 1
        Assert.assertEquals("mail@mail.com", resultKunde1.getEmail());
        Assert.assertEquals("Hans", resultKunde1.getVorname());
        Assert.assertEquals("Müller", resultKunde1.getNachname());
        Assert.assertEquals(35, resultKunde1.getTreuepunkte());
        Assert.assertEquals(13, resultKunde1.getKundenID());

        // Check Customer 2
        Assert.assertEquals("mail@mail.com", resultKunde2.getEmail());
        Assert.assertEquals("Peter", resultKunde2.getVorname());
        Assert.assertEquals("Huber", resultKunde2.getNachname());
        Assert.assertEquals(40, resultKunde2.getTreuepunkte());
        Assert.assertEquals(17, resultKunde2.getKundenID());
    }

    @org.junit.Test
    public void testeFilmFactory() throws Exception
    {
        // Create Resultsets
        resultSetMock13 = Mockito.mock(ResultSet.class);
        resultSetMock14 = Mockito.mock(ResultSet.class);
        resultSetMock15 = Mockito.mock(ResultSet.class);
        resultSetMock16 = Mockito.mock(ResultSet.class);
        resultSetMock17 = Mockito.mock(ResultSet.class);
        resultSetMock18 = Mockito.mock(ResultSet.class);
        resultSetMock19 = Mockito.mock(ResultSet.class);
        resultSetMock20 = Mockito.mock(ResultSet.class);
        resultSetMock21 = Mockito.mock(ResultSet.class);
        resultSetMock22 = Mockito.mock(ResultSet.class);
        resultSetMock23 = Mockito.mock(ResultSet.class);
        resultSetMock24 = Mockito.mock(ResultSet.class);

        // Add values to Resultset
        // Test1
        Mockito.when(resultSetMock13.getString("Titel")).thenReturn("König der Löwen").thenReturn("Toy Story");
        Mockito.when(resultSetMock13.getString("Beschreibung")).thenReturn("Guter Film").thenReturn("sehr guter Film");
        Mockito.when(resultSetMock13.getString("BildLink")).thenReturn("/img/1.jpg").thenReturn("/img/2.jpg");
        Mockito.when(resultSetMock13.getString("TrailerLink")).thenReturn("youtube.com").thenReturn("vimeo.com");
        Mockito.when(resultSetMock13.getInt("Dauer")).thenReturn(160).thenReturn(150);
        Mockito.when(resultSetMock13.getInt("FSK")).thenReturn(0).thenReturn(6);
        Mockito.when(resultSetMock13.getInt("Vorstellung.FilmID")).thenReturn(12).thenReturn(13);
        Mockito.when(resultSetMock13.getBoolean("3D")).thenReturn(true).thenReturn(false);

        Mockito.when(resultSetMock14.getString("Genrebezeichnung")).thenReturn("Animation").thenReturn("Komödie");

        Mockito.when(resultSetMock15.getString("Sprachenname")).thenReturn("deutsch").thenReturn("englisch");

        // Test2
        Mockito.when(resultSetMock16.getString("Titel")).thenReturn("Toy Story").thenReturn("Leberkäs Junge");
        Mockito.when(resultSetMock16.getString("Beschreibung")).thenReturn("Animations Film").thenReturn("sehr guter Film");
        Mockito.when(resultSetMock16.getString("BildLink")).thenReturn("/img/4.jpg").thenReturn("/img/5.jpg");
        Mockito.when(resultSetMock16.getString("TrailerLink")).thenReturn("youtube.com/1").thenReturn("vimeo.com/1");
        Mockito.when(resultSetMock16.getInt("Dauer")).thenReturn(150).thenReturn(170);
        Mockito.when(resultSetMock16.getInt("FSK")).thenReturn(6).thenReturn(6);
        Mockito.when(resultSetMock16.getInt("Vorstellung.FilmID")).thenReturn(15).thenReturn(16);
        Mockito.when(resultSetMock16.getBoolean("3D")).thenReturn(false).thenReturn(false);

        Mockito.when(resultSetMock17.getString("Genrebezeichnung")).thenReturn("Animation").thenReturn("Komödie");

        Mockito.when(resultSetMock18.getString("Sprachenname")).thenReturn("deutsch").thenReturn("türkisch");

        // Test3
        Mockito.when(resultSetMock19.getString("Titel")).thenReturn("Once upon a Time").thenReturn("Leberkäs Junge");
        Mockito.when(resultSetMock19.getString("Beschreibung")).thenReturn("Fantasy").thenReturn("sehr guter Film");
        Mockito.when(resultSetMock19.getString("BildLink")).thenReturn("/img/6.jpg").thenReturn("/img/5.jpg");
        Mockito.when(resultSetMock19.getString("TrailerLink")).thenReturn("youtube.com/2").thenReturn("vimeo.com/1");
        Mockito.when(resultSetMock19.getInt("Dauer")).thenReturn(130).thenReturn(170);
        Mockito.when(resultSetMock19.getInt("FSK")).thenReturn(16).thenReturn(6);
        Mockito.when(resultSetMock19.getInt("Film.FilmID")).thenReturn(17).thenReturn(16);
        Mockito.when(resultSetMock19.getBoolean("3D")).thenReturn(false).thenReturn(true);

        Mockito.when(resultSetMock20.getString("Genrebezeichnung")).thenReturn("Fantasy").thenReturn("Komödie");

        Mockito.when(resultSetMock21.getString("Sprachenname")).thenReturn("englisch").thenReturn("türkisch");

        // Test4
        Mockito.when(resultSetMock22.getString("Titel")).thenReturn("Once upon a Time");
        Mockito.when(resultSetMock22.getString("Beschreibung")).thenReturn("Fantasy");
        Mockito.when(resultSetMock22.getString("BildLink")).thenReturn("/img/6.jpg");
        Mockito.when(resultSetMock22.getString("TrailerLink")).thenReturn("youtube.com/2");
        Mockito.when(resultSetMock22.getInt("Dauer")).thenReturn(130);
        Mockito.when(resultSetMock22.getInt("FSK")).thenReturn(16);
        Mockito.when(resultSetMock22.getInt("Film.FilmID")).thenReturn(17);
        Mockito.when(resultSetMock22.getBoolean("3D")).thenReturn(false);

        Mockito.when(resultSetMock23.getString("Genrebezeichnung")).thenReturn("Fantasy");

        Mockito.when(resultSetMock24.getString("Sprachenname")).thenReturn("englisch");

        // Add next() to ResultSet
        Mockito.when(resultSetMock13.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock14.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock15.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock16.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock17.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock18.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock19.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock20.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock21.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock22.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock23.next()).thenReturn(true).thenReturn(false);
        Mockito.when(resultSetMock24.next()).thenReturn(true).thenReturn(false);

        // Mock for Method:
        //public static Film[] getFilme(String search, String date, String time, int fsk, String plz)
        Film[] resultFilmeArray1 = FilmFactory.getFilme("r","2019-07-07","19:30:00", 6, "68165", resultSetMock13, resultSetMock14, resultSetMock15);

        Assert.assertNotNull(resultFilmeArray1);

        Assert.assertEquals("König der Löwen", resultFilmeArray1[0].getTitel());
        Assert.assertEquals("Toy Story", resultFilmeArray1[1].getTitel());
        Assert.assertEquals("Guter Film", resultFilmeArray1[0].getBeschreibung());
        Assert.assertEquals("sehr guter Film", resultFilmeArray1[1].getBeschreibung());
        Assert.assertEquals("/img/1.jpg", resultFilmeArray1[0].getBildLink());
        Assert.assertEquals("/img/2.jpg", resultFilmeArray1[1].getBildLink());
        Assert.assertEquals("youtube.com", resultFilmeArray1[0].getTrailerLink());
        Assert.assertEquals("vimeo.com", resultFilmeArray1[1].getTrailerLink());
        Assert.assertEquals(160, resultFilmeArray1[0].getDauer());
        Assert.assertEquals(150, resultFilmeArray1[1].getDauer());
        Assert.assertEquals(0, resultFilmeArray1[0].getFsk());
        Assert.assertEquals(6, resultFilmeArray1[1].getFsk());
        Assert.assertEquals(12, resultFilmeArray1[0].getFilmID());
        Assert.assertEquals(13, resultFilmeArray1[1].getFilmID());

        // Mock resultset is null
        Film[] resultFilmeArray2 = FilmFactory.getFilme("r","2019-07-07","19:30:00", 6, "68165", null, resultSetMock14, resultSetMock15);

        Assert.assertNull(resultFilmeArray2);

        // Mock for Method:
        // public static Film[] getFilme(String search, String date, String time, int fsk, String plz, int genreID)
        Film[] resultFilmeArray3 = FilmFactory.getFilme("r", "2019-07-07","19:30:00", 6, "68165", 5, resultSetMock16, resultSetMock17, resultSetMock18);

        Assert.assertNotNull(resultFilmeArray3);

        Assert.assertEquals("Toy Story", resultFilmeArray3[0].getTitel());
        Assert.assertEquals("Leberkäs Junge", resultFilmeArray3[1].getTitel());
        Assert.assertEquals("Animations Film", resultFilmeArray3[0].getBeschreibung());
        Assert.assertEquals("sehr guter Film", resultFilmeArray3[1].getBeschreibung());
        Assert.assertEquals("/img/4.jpg", resultFilmeArray3[0].getBildLink());
        Assert.assertEquals("/img/5.jpg", resultFilmeArray3[1].getBildLink());
        Assert.assertEquals("youtube.com/1", resultFilmeArray3[0].getTrailerLink());
        Assert.assertEquals("vimeo.com/1", resultFilmeArray3[1].getTrailerLink());
        Assert.assertEquals(150, resultFilmeArray3[0].getDauer());
        Assert.assertEquals(170, resultFilmeArray3[1].getDauer());
        Assert.assertEquals(6, resultFilmeArray3[0].getFsk());
        Assert.assertEquals(6, resultFilmeArray3[1].getFsk());
        Assert.assertEquals(15, resultFilmeArray3[0].getFilmID());
        Assert.assertEquals(16, resultFilmeArray3[1].getFilmID());

        // Mock resultset is null
        Film[] resultFilmeArray4 = FilmFactory.getFilme("r", "2019-07-07","19:30:00", 6, "68165", 5, null, resultSetMock17, resultSetMock18);

        Assert.assertNull(resultFilmeArray4);

        // Mock for Method:
        //getTitelPageFilme(String plz)
        Film[] resultFilmeArray5 = FilmFactory.getTitelPageFilme("68165", resultSetMock19, resultSetMock20, resultSetMock21);

        Assert.assertNotNull(resultFilmeArray5);

        Assert.assertEquals("Once upon a Time", resultFilmeArray5[0].getTitel());
        Assert.assertEquals("Leberkäs Junge", resultFilmeArray5[1].getTitel());
        Assert.assertEquals("Fantasy", resultFilmeArray5[0].getBeschreibung());
        Assert.assertEquals("sehr guter Film", resultFilmeArray5[1].getBeschreibung());
        Assert.assertEquals("/img/6.jpg", resultFilmeArray5[0].getBildLink());
        Assert.assertEquals("/img/5.jpg", resultFilmeArray5[1].getBildLink());
        Assert.assertEquals("youtube.com/2", resultFilmeArray5[0].getTrailerLink());
        Assert.assertEquals("vimeo.com/1", resultFilmeArray5[1].getTrailerLink());
        Assert.assertEquals(130, resultFilmeArray5[0].getDauer());
        Assert.assertEquals(170, resultFilmeArray5[1].getDauer());
        Assert.assertEquals(16, resultFilmeArray5[0].getFsk());
        Assert.assertEquals(6, resultFilmeArray5[1].getFsk());
        Assert.assertEquals(17, resultFilmeArray5[0].getFilmID());
        Assert.assertEquals(16, resultFilmeArray5[1].getFilmID());

        // Mock resultset is null
        Film[] resultFilmeArray6 = FilmFactory.getTitelPageFilme("68165", null, resultSetMock20, resultSetMock21);

        Assert.assertNull(resultFilmeArray6);

        // Mock for Method:
        // getFilm(int id)
        Film resultFilm = FilmFactory.getFilm(17, resultSetMock22, resultSetMock23, resultSetMock24);

        Assert.assertNotNull(resultFilm);

        Assert.assertEquals("Once upon a Time", resultFilm.getTitel());

        Assert.assertEquals("Fantasy", resultFilm.getBeschreibung());
        Assert.assertEquals("/img/6.jpg", resultFilm.getBildLink());
        Assert.assertEquals("youtube.com/2", resultFilm.getTrailerLink());
        Assert.assertEquals(130, resultFilm.getDauer());
        Assert.assertEquals(16, resultFilm.getFsk());
        Assert.assertEquals(17, resultFilm.getFilmID());
    }

    @org.junit.Test
    public void testeVorstellungsFactory()
    {

    }

    @org.junit.Test
    public void testeBuchungsFactory()
    {

    }

    @org.junit.Test
    public void testePreisFactory()
    {

    }

    @org.junit.Test
    public void testeProfilFactory()
    {

    }

    @org.junit.Test
    public void testeReservierungsFactory()
    {

    }

    @org.junit.Test
    public void testeSitzSperreFactory()
    {

    }

    // TESTS FOR EXCEPTIONS

    // Tests for UnequalParameterLength Exception
    @org.junit.Test(expected = UnequalParameterLength.class)
    public void testeUnequalParameterLength() throws UnequalParameterLength
    {
        // Create Exception Object
        UnequalParameterLength exception = new UnequalParameterLength();

        // Test Message of Object
        Assert.assertEquals("Unequal length of two input Arrays that need to be equal in length", exception.getMessage());

        // Throw new Exception to catch
        throw new UnequalParameterLength();
    }

    //Mockito.when(resultSetMock1.getString("Straße")).thenReturn("Kurze Straße").thenReturn("Lange Straße").thenReturn("Test Straße");
    //        Mockito.when(resultSetMock1.getString("Hausnummer")).thenReturn("2").thenReturn("4").thenReturn("6");
    //        Mockito.when(resultSetMock1.getString("PLZ")).thenReturn("32839").thenReturn("68165").thenReturn("68163");
    //        Mockito.when(resultSetMock1.getString("Ortsname")).thenReturn("Steinheim").thenReturn("Mannheim").thenReturn("Mannheim");

}