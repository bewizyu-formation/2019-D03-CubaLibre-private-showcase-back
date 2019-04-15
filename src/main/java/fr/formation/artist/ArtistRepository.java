package fr.formation.artist;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Artist repository.
 */
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    /**
     * Find by artist name.
     *
     * @param artistName the artistName
     *
     * @return the Artist
     */
    public Artist findByArtistName(String artistName);

    public Artist update(Artist artist);
}
