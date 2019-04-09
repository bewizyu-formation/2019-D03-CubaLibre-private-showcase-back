package fr.formation.user;

import fr.formation.artist.Artist;

public class UserDto {

    private String username;
    private String password;
    private String email;
    private String city;

    public UserDto(){

    }

    /**
     * Gets username
     *
     * @return value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password
     *
     * @return value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets email
     *
     * @return value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets city
     *
     * @return value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }
}
