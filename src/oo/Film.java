package oo;

public class Film {
    String titel, beschreibung, bildLink, trailerLink;
    int dauer, fsk, filmID;
    boolean dreiD;
    //fremdschlüssel
    String genre[], sprache[];

    public Film(String titel, String beschreibung, String bildLink, String trailerLink, int dauer, int fsk, int filmID, boolean dreiD, String[] genre, String[] sprache) {
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.bildLink = bildLink;
        this.trailerLink = trailerLink;
        this.dauer = dauer;
        this.fsk = fsk;
        this.filmID = filmID;
        this.dreiD = dreiD;
        this.genre = genre;
        this.sprache = sprache;
    }

    public Film(String titel, String beschreibung, String bildLink, String trailerLink, int dauer, int fsk, int filmID, boolean dreiD) {
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.bildLink = bildLink;
        this.trailerLink = trailerLink;
        this.dauer = dauer;
        this.fsk = fsk;
        this.filmID = filmID;
        this.dreiD = dreiD;
    }

    public Film(int filmID) {
        this.filmID = filmID;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String getGenreString() {
        String genreString = "";
        for(int i = 0; i < genre.length; i++) {
            genreString += genre[i];
            if(i != genre.length - 1) {
                genreString += ", ";
            }
        }
                return genreString;
    }

    public String[] getSprache() {
        return sprache;
    }

    public void setSprache(String[] sprache) {
        this.sprache = sprache;
    }

    public String getSpracheString() {
        String spracheString = "";
        for(int i = 0; i < sprache.length; i++) {
            spracheString += sprache[i];
            if(i != sprache.length - 1) {
                spracheString += ", ";
            }
        }
        return spracheString;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBildLink() {
        return bildLink;
    }

    public void setBildLink(String bildLink) {
        this.bildLink = bildLink;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public int getFsk() {
        return fsk;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public boolean isDreiD() {
        return dreiD;
    }

    public void setDreiD(boolean dreiD) {
        this.dreiD = dreiD;
    }
}
