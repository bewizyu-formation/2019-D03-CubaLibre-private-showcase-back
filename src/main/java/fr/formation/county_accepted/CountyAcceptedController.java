package fr.formation.county_accepted;

import fr.formation.artist.ArtistService;
import fr.formation.controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * The type CountyAccepted controller.
 */
@RestController
@RequestMapping("/home")
public class CountyAcceptedController extends AbstractController {

    @Autowired
    private ArtistService artistService;




}
