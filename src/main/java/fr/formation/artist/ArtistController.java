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
     * getArtistsByDepartment.
     */
    @GetMapping("/")
    public List<Artist> getArtistsByDepartment() {
        User user = getAuthenticatedUser();
        return artistService.getArtistByDepartement(Integer.parseInt(user.getCodeDepartment()));
    }

    @GetMapping("/all/")
    public List<Artist> getArtistsList() {
        return artistService.getArtistsList();

    }

    @GetMapping("/{artistName}")
    public Artist findByArtistName(@PathVariable("artistName") String artistName){
        return artistService.getArtistByName(artistName);
    }
}
