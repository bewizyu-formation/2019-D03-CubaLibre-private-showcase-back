package fr.formation.event;

import java.util.Date;
import java.util.Set;

public class EventDTO {

    private Long id;
    private Date date;
    private String artistName;
    private Set<Long> confirmedIdList;
    private Set<Long> invitatedIdList;
    private String organiserUsername;
    private int maxConfirmed;

    /**
     * Gets id
     *
     * @return value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id the id
     */
    public void setId(long id) {
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
     * Gets confirmedIdList
     *
     * @return value of confirmedIdList
     */
    public Set<Long> getConfirmedIdList() {
        return confirmedIdList;
    }

    /**
     * Sets confirmedIdList
     *
     * @param confirmedIdList the confirmedIdList
     */
    public void setConfirmedIdList(Set<Long> confirmedIdList) {
        this.confirmedIdList = confirmedIdList;
    }

    /**
     * Gets invitatedIdList
     *
     * @return value of invitatedIdList
     */
    public Set<Long> getInvitatedIdList() {
        return invitatedIdList;
    }

    /**
     * Sets invitatedIdList
     *
     * @param invitatedIdList the invitatedIdList
     */
    public void setInvitatedIdList(Set<Long> invitatedIdList) {
        this.invitatedIdList = invitatedIdList;
    }

    /**
     * Gets organiserUsername
     *
     * @return value of organiserUsername
     */
    public String getOrganiserUsername() {
        return organiserUsername;
    }

    /**
     * Sets organiserUsername
     *
     * @param organiserUsername the organiserUsername
     */
    public void setOrganiserUsername(String organiserUsername) {
        this.organiserUsername = organiserUsername;
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
