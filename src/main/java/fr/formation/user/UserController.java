package fr.formation.user;

import fr.formation.artist.ArtistService;
import fr.formation.departement_accepted.DepartementAcceptedService;
import fr.formation.geo.services.impl.CommuneServiceImpl;
import fr.formation.user.exceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedHashMap;
import java.util.List;

import static fr.formation.security.SecurityConstants.ROLE_USER;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private DepartementAcceptedService departementAcceptedService;
	@Autowired
	private CommuneServiceImpl communeServiceImpl;

	/**
	 * Signup.
	 *
	 * @param user the user
	 */
	@PutMapping("/")
	public void signup(@RequestBody User user) throws InvalidPasswordException {
	    try {
            userService.addNewUser(user, ROLE_USER);
            /*if(user.getArtist()!=null){
				artistService.addNewArtist(user.getArtist());
				List<LinkedHashMap> test = communeServiceImpl.getCommunes(user.getCity());
				LinkedHashMap<String, String> testc = test.get(0);
				String t = testc.get("codeDepartement");
				departementAcceptedService.addNewDepartementAcceptedService(Integer.parseInt(t/*communeServiceImpl.getCommunes(user.getCity()).get(0).getCodeDepartement()), user.getArtist());
			}*/
        } catch(InvalidPasswordException e){

        }
	}

}
