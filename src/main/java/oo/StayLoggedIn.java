package oo;

public class StayLoggedIn {
    private String id;
    private String email;
    private String passwordHash;

    /**
     *
     * @param id
     * @param email
     * @param passwordHash
     */
    public StayLoggedIn(String id, String email, String passwordHash) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    /**
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     *
     * @return passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     *
     * @param passwordHash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
