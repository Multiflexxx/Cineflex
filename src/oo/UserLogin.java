package oo;

public class UserLogin {
    private String email;
    private String firstname;
    private String lastname;
    private int PID;
    private int KID;

    public UserLogin(String email, String firstname, String lastname, int PID, int KID) {
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.PID = PID;
        this.KID = KID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getKID() {
        return KID;
    }

    public void setKID(int KID) {
        this.KID = KID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
