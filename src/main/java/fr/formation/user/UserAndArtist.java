package fr.formation.user;

import fr.formation.artist.Artist;

public class UserAndArtist {
    public Artist artist;
    public UserDTO user;

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
     * Gets user
     *
     * @return value of user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Sets user
     *
     * @param user the user
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }
}
