package fr.formation.event;


import fr.formation.artist.Artist;
import fr.formation.user.User;

import javax.persistence.*;
import java.sql.Date;
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

    private Date date;

    @ManyToOne
    private Artist artist;

    @ManyToMany
    private Set<User> confirmedUserList;

    @ManyToMany
    private Set<User> invitatedUserList;

    @ManyToOne
    private User organizer;

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
    public Date getDate() {
        return date;
    }

    /**
     * Sets date
     *
     * @param date the date
     */
    public void setDate(Date date) {
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
     * Gets organizer
     *
     * @return value of organizer
     */
    public User getOrganizer() {
        return organizer;
    }

    /**
     * Sets organizer
     *
     * @param organizer the organizer
     */
    public void setOrganizer(User organizer) {
        this.organizer = organizer;
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
}
