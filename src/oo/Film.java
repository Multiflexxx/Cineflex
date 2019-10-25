package oo;

public class Film {
    String titel, beschreibung, bildLink, trailerLink;
    int dauer, fsk, filmID;
    boolean dreiD;
    //fremdschlüssel
    String genre[], sprache[];

    /**
     *
     * @param titel
     * @param beschreibung
     * @param bildLink
     * @param trailerLink
     * @param dauer
     * @param fsk
     * @param filmID
     * @param dreiD
     * @param genre
     * @param sprache
     */
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

    /**
     *
     * @param titel
     * @param beschreibung
     * @param bildLink
     * @param trailerLink
     * @param dauer
     * @param fsk
     * @param filmID
     * @param dreiD
     */
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

    /**
     *
     * @param filmID
     */
    public Film(int filmID) {
        this.filmID = filmID;
    }

    /**
     *
     * @return genre
     */
    public String[] getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     */
    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    /**
     *
     * @return genreString
     */
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

    /**
     *
     * @return sprache
     */
    public String[] getSprache() {
        return sprache;
    }

    /**
     *
     * @param sprache
     */
    public void setSprache(String[] sprache) {
        this.sprache = sprache;
    }

    /**
     *
     * @return spracheString
     */
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

    /**
     *
     * @return titel
     */
    public String getTitel() {
        return titel;
    }

    /**
     *
     * @param titel
     */
    public void setTitel(String titel) {
        this.titel = titel;
    }

    /**
     *
     * @return beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     *
     * @param beschreibung
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     *
     * @return bildLink
     */
    public String getBildLink() {
        return bildLink;
    }

    /**
     *
     * @param bildLink
     */
    public void setBildLink(String bildLink) {
        this.bildLink = bildLink;
    }

    /**
     *
     * @return trailerLink
     */
    public String getTrailerLink() {
        return trailerLink;
    }

    /**
     *
     * @param trailerLink
     */
    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    /**
     *
     * @return dauer
     */
    public int getDauer() {
        return dauer;
    }

    /**
     *
     * @param dauer
     */
    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    /**
     *
     * @return fsk
     */
    public int getFsk() {
        return fsk;
    }

    /**
     *
     * @param fsk
     */
    public void setFsk(int fsk) {
        this.fsk = fsk;
    }

    /**
     *
     * @return filmID
     */
    public int getFilmID() {
        return filmID;
    }

    /**
     *
     * @param filmID
     */
    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    /**
     *
     * @return dreiD
     */
    public boolean isDreiD() {
        return dreiD;
    }

    /**
     *
     * @param dreiD
     */
    public void setDreiD(boolean dreiD) {
        this.dreiD = dreiD;
    }
}
