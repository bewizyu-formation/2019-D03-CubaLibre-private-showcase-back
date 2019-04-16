package fr.formation.user;

import fr.formation.artist.ArtistDTO;

public class UserAndArtist {
    public ArtistDTO artist;
    public UserDTO user;

    public UserAndArtist(){

    }

    public UserAndArtist(UserDTO user, ArtistDTO artist){
        this.artist = artist;
        this.user = user;
    }

    /**
     * Gets artist
     *
     * @return value of artist
     */
    public ArtistDTO getArtist() {
        return artist;
    }

    /**
     * Sets artist
     *
     * @param artist the artist
     */
    public void setArtist(ArtistDTO artist) {
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
