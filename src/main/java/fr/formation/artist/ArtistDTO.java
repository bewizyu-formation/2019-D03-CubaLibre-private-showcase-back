package fr.formation.artist;

import java.util.Set;

public class ArtistDTO {

    private Long id;
    private String artistName;
    private String shortDescription;
    private String longDescription;
    private int voteNumber = 0;
    private int rating;
    private String website;
    private String phone;
    private String address;

    private String picture;
    private Set<Long> eventIdList;

    public ArtistDTO(){

    }

    public ArtistDTO(String artistName,
                     String shortDescription,
                     String longDescription,
                     int voteNumber,
                     int rating,
                     String website,
                     String phone,
                     String address,
                     String picture) {
        this.artistName = artistName;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.voteNumber = voteNumber;
        this.rating = rating;
        this.website = website;
        this.phone = phone;
        this.address = address;
        this.picture = picture;
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
     * Gets longDescription
     *
     * @return value of longDescription
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
     * Gets shortDescription
     *
     * @return value of shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets shortDescription
     *
     * @param shortDescription the shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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
    public String getPicture() {
        return picture;
    }

    /**
     * Sets picture
     *
     * @param picture the picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * Gets eventIdList
     *
     * @return value of eventIdList
     */
    public Set<Long> getEventIdList() {
        return eventIdList;
    }

    /**
     * Sets eventIdList
     *
     * @param eventIdList the eventIdList
     */
    public void setEventIdList(Set<Long> eventIdList) {
        this.eventIdList = eventIdList;
    }

    /**
     * Gets Id
     *
     * @return value of ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets Id
     *
     * @param id the Id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
