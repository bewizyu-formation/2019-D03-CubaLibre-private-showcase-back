package fr.formation.event;


import fr.formation.artist.Artist;
import fr.formation.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

/**
 * The type Event.
 *
 */
@Entity
@Table()
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private Artist artist;

    @ManyToMany
    private Set<User> confirmedUserList;

    @ManyToMany
    private Set<User> invitatedUserList;

    @ManyToOne
    private User organiser;

    private int maxConfirmed;

    /**
     * Gets id
     *
     * @return value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets date
     *
     * @return value of date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets date
     *
     * @param date the date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets artist
     *
     * @return value of artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Sets artist
     *
     * @param artist the artist
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Gets organiser
     *
     * @return value of organiser
     */
    public User getOrganiser() {
        return organiser;
    }

    /**
     * Sets organiser
     *
     * @param organiser the organiser
     */
    public void setOrganiser(User organiser) {
        this.organiser = organiser;
    }

    /**
     * Gets confirmedUserList
     *
     * @return value of confirmedUserList
     */
    public Set<User> getConfirmedUserList() {
        return confirmedUserList;
    }

    /**
     * Sets confirmedUserList
     *
     * @param confirmedUserList the confirmedUserList
     */
    public void setConfirmedUserList(Set<User> confirmedUserList) {
        this.confirmedUserList = confirmedUserList;
    }

    /**
     * Gets invitatedUserList
     *
     * @return value of invitatedUserList
     */
    public Set<User> getInvitatedUserList() {
        return invitatedUserList;
    }

    /**
     * Sets invitatedUserList
     *
     * @param invitatedUserList the invitatedUserList
     */
    public void setInvitatedUserList(Set<User> invitatedUserList) {
        this.invitatedUserList = invitatedUserList;
    }

    /**
     * Gets maxConfirmed
     *
     * @return value of maxConfirmed
     */
    public int getMaxConfirmed() {
        return maxConfirmed;
    }

    /**
     * Sets maxConfirmed
     *
     * @param maxConfirmed the maxConfirmed
     */
    public void setMaxConfirmed(int maxConfirmed) {
        this.maxConfirmed = maxConfirmed;
    }
}
