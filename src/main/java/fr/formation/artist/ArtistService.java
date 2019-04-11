package fr.formation.artist;

import fr.formation.departement_accepted.DepartementAccepted;
import fr.formation.departement_accepted.DepartementAcceptedRepository;
import fr.formation.departement_accepted.DepartementAcceptedService;
import fr.formation.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Artist service.
 */

@Service
public class ArtistService {

    private ArtistRepository artistRepository;

    private DepartementAcceptedService departementAcceptedService;

    private DepartementAcceptedRepository departementAcceptedRepository;

    private static final Logger log = LoggerFactory.getLogger(ArtistService.class);


    /**
     * Instantiates a new User service.
     *
     * @param artistRepository the artist repository
     */
    @Autowired
    public ArtistService(DepartementAcceptedRepository departementAcceptedRepository, ArtistRepository artistRepository, DepartementAcceptedService departementAcceptedService) {
        this.artistRepository = artistRepository;
        this.departementAcceptedService = departementAcceptedService;
        this.departementAcceptedRepository = departementAcceptedRepository;
    }

    public void addNewArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public List<Artist> getArtistByDepartement(int code){
        List<DepartementAccepted> listDepartementAccepted = departementAcceptedRepository.findByCode(code);
        log.debug("Size of listDepartement" + listDepartementAccepted.size());
        List<Artist> artists = listDepartementAccepted
                .stream()
                .map(depAccepted ->  depAccepted.getArtist())
                .collect(Collectors.toList());

        log.debug("Size of listDepartement" + artists.size());

        return artists;
    }

    public List<Artist> getArtistsList(){
        List<Artist> artists = artistRepository.findAll();
        return artists;
    }

    public Artist getArtistByName(String artistName){
        Artist artist = artistRepository.findByArtistName(artistName);
        return artist;
    }
}
