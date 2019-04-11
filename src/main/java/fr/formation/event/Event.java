package fr.formation.event;


import fr.formation.artist.Artist;
import fr.formation.user.User;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

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
    private Set<User> userList;

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
     * Gets userList
     *
     * @return value of userList
     */
    public Set<User> getUserList() {
        return userList;
    }

    /**
     * Sets userList
     *
     * @param userList the userList
     */
    public void setUserList(Set<User> userList) {
        this.userList = userList;
    }
}
