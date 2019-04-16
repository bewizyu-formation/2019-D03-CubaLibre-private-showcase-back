package fr.formation.county_accepted;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistRepository;
import fr.formation.artist.ArtistService;
import fr.formation.controllers.AbstractController;
import fr.formation.geo.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The type CountyAccepted controller.
 */
@RestController
@RequestMapping("/county")
public class CountyAcceptedController extends AbstractController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private CountyAcceptedService countyAcceptedService;

    @Autowired
    private DepartementService departementService;

    @GetMapping("/{artistName}")
    public List<LinkedHashMap> getCountyByArtist(@PathVariable("artistName") String artistName) {

            List<CountyAccepted> countyAcceptedByArtist = this.countyAcceptedService.getCountyByArtist(artistRepository.findByArtistName(artistName));
            List<LinkedHashMap> nameCountyAcceptedByArtist = countyAcceptedByArtist
                    .stream()
                    .map(
                            c -> {
                                try{
                                    return departementService.getDepartementByCode("" + c.getCode()).get(0);
                                }catch (UnsupportedEncodingException e){
                                    e.printStackTrace();
                                }
                                return null;
                            })
                    .collect(Collectors.toList());
            return nameCountyAcceptedByArtist;
    }




}
