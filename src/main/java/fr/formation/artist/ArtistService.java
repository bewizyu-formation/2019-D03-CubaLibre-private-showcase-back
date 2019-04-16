package fr.formation.artist;

import fr.formation.county_accepted.CountyAccepted;
import fr.formation.county_accepted.CountyAcceptedRepository;
import fr.formation.county_accepted.CountyAcceptedService;
import fr.formation.event.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Artist service.
 */

@Service
public class ArtistService {

    private ArtistRepository artistRepository;

    private EventService eventService;

    private CountyAcceptedService countyAcceptedService;

    private CountyAcceptedRepository countyAcceptedRepository;

    private static final Logger log = LoggerFactory.getLogger(ArtistService.class);


    /**
     * Instantiates a new User service.
     *
     * @param artistRepository the artist repository
     */
    @Autowired
    public ArtistService(EventService eventService,
                         CountyAcceptedRepository countyAcceptedRepository,
                         ArtistRepository artistRepository,
                         CountyAcceptedService countyAcceptedService) {

        this.eventService = eventService;
        this.artistRepository = artistRepository;
        this.countyAcceptedService = countyAcceptedService;
        this.countyAcceptedRepository = countyAcceptedRepository;
    }


    public void saveArtist(ArtistDTO artistDTO) throws UnsupportedEncodingException {
        Artist artist = createArtist(artistDTO);
        artistRepository.save(artist);
    }

    public List<ArtistDTO> getArtistByDepartementAcceptedCode(int code) {
        List<CountyAccepted> listCountyAccepted = countyAcceptedRepository.findByCode(code);
        log.debug("Size of listDepartement" + listCountyAccepted.size());
        List<ArtistDTO> artistDTOs = listCountyAccepted
                .stream()
                .map(depAccepted -> depAccepted.getArtist())
                .map(this::createArtistDTO)
                .collect(Collectors.toList());
        return artistDTOs;
    }

    public List<ArtistDTO> getArtistsList() {
        return artistRepository
                .findAll()
                .stream()
                .map(this::createArtistDTO)
                .collect(Collectors.toList());
    }

    public ArtistDTO findByArtistName(String artistName) throws UnsupportedEncodingException {
        return createArtistDTO(artistRepository.findByArtistName(artistName));
    }

    public ArtistDTO findById(Long id) {
        return createArtistDTO(artistRepository.findById(id).get());
    }


    public ArtistDTO createArtistDTO(Artist artist) {
        ArtistDTO artistDTO = new ArtistDTO();

        artistDTO.setArtistName(artist.getArtistName());
        artistDTO.setShortDescription(artist.getShortDescription());
        artistDTO.setLongDescription(artist.getLongDescription());
        artistDTO.setVoteNumber(artist.getVoteNumber());
        artistDTO.setRating(artist.getRating());

        if (artist.getWebsite() != null) {
            artistDTO.setWebsite(artist.getWebsite());
        }

        if (artist.getPhone() != null) {
            artistDTO.setPhone(artist.getPhone());
        }

        if (artist.getAddress() != null) {
            artistDTO.setAddress(artist.getAddress());
        }

        try {
            if (artist.getPicture() != null) {
                artistDTO.setPicture(new String(artist.getPicture(), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            log.info(e.getMessage());
        }

        if (artist.getEventList() != null) {
            artistDTO.setEventIdList(
                    artist.getEventList()
                            .stream()
                            .map(e -> e.getId())
                            .collect(Collectors.toSet())
            );
        }

        return artistDTO;
    }

    public Artist createArtist(ArtistDTO artistDTO) {
        Artist artist = new Artist();

        artist.setArtistName(artistDTO.getArtistName());
        artist.setShortDescription(artistDTO.getShortDescription());
        artist.setLongDescription(artistDTO.getLongDescription());
        artist.setVoteNumber(artistDTO.getVoteNumber());
        artist.setRating(artistDTO.getRating());

        if (artistDTO.getWebsite() != null) {
            artist.setWebsite(artistDTO.getWebsite());
        }

        if (artistDTO.getPhone() != null) {
            artist.setPhone(artistDTO.getPhone());
        }

        if (artistDTO.getAddress() != null) {
            artist.setAddress(artistDTO.getAddress());
        }

        if (artistDTO.getPicture() != null) {
            artist.setPicture(artistDTO.getPicture().getBytes());
        }

        if (artistDTO.getEventIdList() != null) {
            artist.setEventList(
                    artistDTO.getEventIdList()
                            .stream()
                            .map(eId -> eventService.findById(eId))
                            .collect(Collectors.toSet())
            );
        }

        return artist;
    }

    public void saveArtist(Artist artist){artistRepository.save(artist);}
}
