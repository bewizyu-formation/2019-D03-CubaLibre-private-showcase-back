package fr.formation.artist;

import fr.formation.county_accepted.CountyAccepted;
import fr.formation.county_accepted.CountyAcceptedRepository;
import fr.formation.county_accepted.CountyAcceptedService;
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

    private CountyAcceptedService countyAcceptedService;

    private CountyAcceptedRepository countyAcceptedRepository;

    private static final Logger log = LoggerFactory.getLogger(ArtistService.class);


    /**
     * Instantiates a new User service.
     *
     * @param artistRepository the artist repository
     */
    @Autowired
    public ArtistService(CountyAcceptedRepository countyAcceptedRepository, ArtistRepository artistRepository, CountyAcceptedService countyAcceptedService) {
        this.artistRepository = artistRepository;
        this.countyAcceptedService = countyAcceptedService;
        this.countyAcceptedRepository = countyAcceptedRepository;
    }

    public void saveArtist(Artist artist) {
        artistRepository.save(artist);
    }

    public List<Artist> getArtistByDepartement(int code){
        List<CountyAccepted> listCountyAccepted = countyAcceptedRepository.findByCode(code);
        log.debug("Size of listDepartement" + listCountyAccepted.size());
        List<Artist> artists = listCountyAccepted
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
