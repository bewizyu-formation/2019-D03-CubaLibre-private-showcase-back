package fr.formation.user;

import fr.formation.artist.Artist;
import fr.formation.event.Event;

import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.Set;

public class UserDTO {

    private String username;
    private String password;
    private String email;
    private String city;
    private String artistName;
    private Set<Long> eventIdInvitatedList;
    private Set<Long> eventIdConfirmedList;

    public UserDTO(){

    }

    public UserDTO(String username, String password, String email, String city, String artistName, Set<Long> eventIdInvitatedList, Set<Long> eventIdConfirmedList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
        this.artistName = artistName;
        this.eventIdInvitatedList = eventIdInvitatedList;
        this.eventIdConfirmedList = eventIdConfirmedList;
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

    /**
     * Gets artistName
     *
     * @return value of artistName
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Sets artistName
     *
     * @param artistName the artistName
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Gets eventIdInvitatedList
     *
     * @return value of eventIdInvitatedList
     */
    public Set<Long> getEventIdInvitatedList() {
        return eventIdInvitatedList;
    }

    /**
     * Sets eventIdInvitatedList
     *
     * @param eventIdInvitatedList the eventIdInvitatedList
     */
    public void setEventIdInvitatedList(Set<Long> eventIdInvitatedList) {
        this.eventIdInvitatedList = eventIdInvitatedList;
    }

    /**
     * Gets eventIdConfirmedList
     *
     * @return value of eventIdConfirmedList
     */
    public Set<Long> getEventIdConfirmedList() {
        return eventIdConfirmedList;
    }

    /**
     * Sets eventIdConfirmedList
     *
     * @param eventIdConfirmedList the eventIdConfirmedList
     */
    public void setEventIdConfirmedList(Set<Long> eventIdConfirmedList) {
        this.eventIdConfirmedList = eventIdConfirmedList;
    }
}