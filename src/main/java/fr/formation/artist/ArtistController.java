package fr.formation.artist;

import fr.formation.controllers.AbstractController;
import fr.formation.user.User;
import fr.formation.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<Artist> getArtistsByCounty() {
        User user = getAuthenticatedUser();
        return artistService.getArtistByDepartement(Integer.parseInt(user.getCodeCounty()));
    }

    @GetMapping("/all/")
    public List<Artist> getArtistsList() {
        return artistService.getArtistsList();

    }

    @GetMapping("/{artistName}")
    public Artist findByArtistName(@PathVariable("artistName") String artistName) {
        return artistService.findByArtistName(artistName);
    }

    @PostMapping("/picture")
    public void putArtistPicture(@RequestParam("artistName") String artistName, @RequestParam("name") String name, @RequestParam("file") MultipartFile file)
            throws IOException {
        if (!file.isEmpty()) {
            log.info("File Name : " + name);
            log.info("File Type : " + file.getContentType());

            byte[] bytesImage = file.getBytes();

            Artist artistWithImage = artistService.findByArtistName(artistName);
            artistWithImage.setPicture(bytesImage);
            artistService.saveArtist(artistWithImage);
        }
    }

    @PostMapping("/update")
    public void updateArtist(@RequestBody ArtistDTO artistDto){
        User user = getAuthenticatedUser();
        Artist oldArtist = user.getArtist();
        Artist artistToAdd = artistService.createArtist(artistDto);
        artistToAdd.setId(oldArtist.getId());
        artistService.saveArtist(artistToAdd);
    }

}
