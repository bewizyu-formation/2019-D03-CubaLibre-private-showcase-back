package fr.formation.artist;


import fr.formation.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @Column
    private String artistName;

    @NotNull
    @Column
    private String description;

    @Column
    private String website;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private int voteNumber = 0;

    @Min(0)
    @Max(10)
    @Column
    private int rating;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] picture;

    public Artist(){
    }

    public Artist(@NotNull String artistName, @NotNull String description, String website, String phone, String address, @NotNull int voteNumber, @Min(0) @Max(10) int rating, byte[] picture) {
        this.artistName = artistName;
        this.description = description;
        this.website = website;
        this.phone = phone;
        this.address = address;
        this.voteNumber = voteNumber;
        this.rating = rating;
        this.picture = picture;
    }

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
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
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
}
