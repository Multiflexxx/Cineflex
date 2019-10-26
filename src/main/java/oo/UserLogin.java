package oo;

public class UserLogin {
    private String email;
    private String firstname;
    private String lastname;
    private int PID;
    private int KID;

    /**
     *
     * @param email
     * @param firstname
     * @param lastname
     * @param PID
     * @param KID
     */
    public UserLogin(String email, String firstname, String lastname, int PID, int KID) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.PID = PID;
        this.KID = KID;
    }

    /**
     *
     * @return firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return PID
     */
    public int getPID() {
        return PID;
    }

    /**
     *
     * @param PID
     */
    public void setPID(int PID) {
        this.PID = PID;
    }

    /**
     *
     * @return KID
     */
    public int getKID() {
        return KID;
    }

    /**
     *
     * @param KID
     */
    public void setKID(int KID) {
        this.KID = KID;
    }

    /**
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
