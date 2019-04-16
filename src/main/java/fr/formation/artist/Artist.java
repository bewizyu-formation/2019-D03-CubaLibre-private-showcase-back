package fr.formation.artist;


import fr.formation.event.Event;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * The type artist
 */
@Entity
@Table()
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String artistName;

    private String longDescription;

    @NotNull
    private String shortDescription;

    private String website;

    private String phone;

    private String address;

    private int voteNumber = 0;

    @Min(0)
    @Max(10)
    private int rating;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] picture;

    @OneToMany(mappedBy = "artist")
    private Set<Event> eventList;

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
     * Gets description
     *
     * @return value of description
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Sets longDescription
     *
     * @param longDescription the longDescription
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * Gets website
     *
     * @return value of website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets website
     *
     * @param website the website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Gets phone
     *
     * @return value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets address
     *
     * @return value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets voteNumber
     *
     * @return value of voteNumber
     */
    public int getVoteNumber() {
        return voteNumber;
    }

    /**
     * Sets voteNumber
     *
     * @param voteNumber the voteNumber
     */
    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
    }

    /**
     * Gets rating
     *
     * @return value of rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets rating
     *
     * @param rating the rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Gets picture
     *
     * @return value of picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Sets picture
     *
     * @param picture the picture
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    /**
     * Gets shortDescription
     *
     * @return value of shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets shortDescrption
     *
     * @param shortDescription the shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }


    /**
     * Gets eventList
     *
     * @return value of eventList
     */
    public Set<Event> getEventList() {
        return eventList;
    }

    /**
     * Sets eventList
     *
     * @param eventList the eventList
     */
    public void setEventList(Set<Event> eventList) {
        this.eventList = eventList;
    }
}
