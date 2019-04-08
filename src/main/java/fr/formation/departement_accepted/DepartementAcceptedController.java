package fr.formation.departement_accepted;

import fr.formation.artist.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * The type DepartementAccepted controller.
 */
@RestController
@RequestMapping("/home")
public class DepartementAcceptedController {

    @Autowired
    private DepartementAcceptedService departementAcceptedService;

    /**
     * getArtistsByDepartment.
     *
     * @param code the code

     */
    @PutMapping("/")
    public List<Artist> getArtistsByDepartment(@RequestParam int code) {

        return departementAcceptedService.getArtistByDepartement(code);

    }

}
