package fr.formation.county_accepted;

import fr.formation.artist.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Artist service.
 */
@Service
public class CountyAcceptedService {

    private CountyAcceptedRepository countyAcceptedRepository;

    /**
     * Instantiates a new CountyAccepted service.
     *
     * @param countyAcceptedRepository the artist repository
     */

    @Autowired
    public CountyAcceptedService(CountyAcceptedRepository countyAcceptedRepository) {
        this.countyAcceptedRepository = countyAcceptedRepository;
    }



    public void addCountyAccepted(int code, Artist artist){
        CountyAccepted countyAccepted = new CountyAccepted();
        countyAccepted.setArtist(artist);
        countyAccepted.setCode(code);

        countyAcceptedRepository.save(countyAccepted);
    }

    public List<CountyAccepted> getCountyByArtist(Artist artist){
        return this.countyAcceptedRepository.findByArtistId(artist.getId());
    }
}
