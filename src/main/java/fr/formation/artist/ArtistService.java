package fr.formation.artist;

import fr.formation.departement_accepted.DepartementAcceptedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Artist service.
 */

@Service
public class ArtistService {

    private ArtistRepository artistRepository;

    private DepartementAcceptedService departementAcceptedService;

    /**
     * Instantiates a new User service.
     *
     * @param artistRepository the artist repository
     */
    @Autowired
    public ArtistService(ArtistRepository artistRepository, DepartementAcceptedService departementAcceptedService) {
        this.artistRepository = artistRepository;
        this.departementAcceptedService = departementAcceptedService;
    }

    public void addNewArtist(Artist artist) {
        artistRepository.save(artist);
    }


}
