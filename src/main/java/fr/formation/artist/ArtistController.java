package fr.formation.artist;

import fr.formation.controllers.AbstractController;
import fr.formation.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController extends AbstractController {

    @Autowired
    ArtistService artistService;

    @PutMapping("/")
    public void signup(@RequestBody Artist artist) {
        artistService.addNewArtist(artist);

    }

    /**
     * getArtistsByCounty.
     */
    @GetMapping("/")
    public List<Artist> getArtistsByCounty() {
        User user = getAuthenticatedUser();
        return artistService.getArtistByDepartement(Integer.parseInt(user.getCodeCounty()));
    }

    @GetMapping("/all/")
    public List<Artist> getArtistsList() {
        return artistService.getArtistsList();

    }

}
