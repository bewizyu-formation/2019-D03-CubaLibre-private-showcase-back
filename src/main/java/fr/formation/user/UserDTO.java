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
    private long artistId;
    private Set<Long> eventIdInvitatedList;
    private Set<Long> eventIdConfirmedList;

    public UserDTO(){

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
     * Gets artistId
     *
     * @return value of artistId
     */
    public long getArtistId() {
        return artistId;
    }

    /**
     * Sets artistId
     *
     * @param artistId the artistId
     */
    public void setArtistId(long artistId) {
        this.artistId = artistId;
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
