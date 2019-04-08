package fr.formation.departement_accepted;

import fr.formation.artist.Artist;
import fr.formation.controllers.AbstractController;
import fr.formation.user.User;
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
public class DepartementAcceptedController extends AbstractController {

    @Autowired
    private DepartementAcceptedService departementAcceptedService;


    /**
     * getArtistsByDepartment.
     */
    @PutMapping("/")
    public List<Artist> getArtistsByDepartment() {
        User user = getAuthenticatedUser();
        return departementAcceptedService.getArtistByDepartement(Integer.parseInt(user.getCodeDepartment()));

    }

}
