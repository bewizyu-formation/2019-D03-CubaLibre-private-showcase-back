package fr.formation.event;

import java.util.Date;
import java.util.Set;

public class EventDTO {

    private Date date;
    private String artistName;
    private Set<String> confirmedUsernameList;
    private Set<String> invitatedUsernameList;
    private String organizerUsername;
    private int maxConfirmed;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public Set<String> getConfirmedUsernameList() {
        return confirmedUsernameList;
    }

    public void setConfirmedUsernameList(Set<String> confirmedUsernameList) {
        this.confirmedUsernameList = confirmedUsernameList;
    }

    public Set<String> getInvitatedUsernameList() {
        return invitatedUsernameList;
    }

    public void setInvitatedUsernameList(Set<String> invitatedUsernameList) {
        this.invitatedUsernameList = invitatedUsernameList;
    }

    public String getOrganizerUsername() {
        return organizerUsername;
    }

    public void setOrganizerUsername(String organizerUsername) {
        this.organizerUsername = organizerUsername;
    }

    public int getMaxConfirmed() {
        return maxConfirmed;
    }

    public void setMaxConfirmed(int maxConfirmed) {
        this.maxConfirmed = maxConfirmed;
    }
}
