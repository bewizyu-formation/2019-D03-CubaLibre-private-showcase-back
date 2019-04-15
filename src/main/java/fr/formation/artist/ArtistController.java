package fr.formation.artist;

import fr.formation.controllers.AbstractController;
import fr.formation.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController extends AbstractController {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistRepository artistRepository;

    private static final Logger log = LoggerFactory.getLogger(ArtistController.class);

    /**
     * getArtistsByCounty.
     */
    @GetMapping("/")
    public List<ArtistDTO> getArtistsByCounty() {
        User user = getAuthenticatedUser();
        return artistService.getArtistByDepartementAcceptedCode(Integer.parseInt(user.getCodeCounty()));
    }

    @GetMapping("/all/")
    public List<ArtistDTO> getArtistsList() {
        return artistService.getArtistsList();

    }

    @GetMapping("/{artistName}")
    public ArtistDTO findByArtistName(@PathVariable("artistName") String artistName) throws UnsupportedEncodingException {
        return artistService.findByArtistName(artistName);
    }

    @PutMapping("/")
    public void saveArtist(@RequestBody ArtistDTO artistDTO) throws UnsupportedEncodingException{
        artistService.saveArtist(artistDTO);
    }




}
