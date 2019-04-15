package fr.formation.user;


import fr.formation.artist.Artist;
import fr.formation.artist.ArtistService;
import fr.formation.controllers.AbstractController;
import fr.formation.user.exceptions.InvalidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.io.UnsupportedEncodingException;

import static fr.formation.security.SecurityConstants.ROLE_USER;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController extends AbstractController {


	@Autowired
	private UserService userService;

	@Autowired
	private ArtistService artistService;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);



	/**
	 * Signup.
	 *
	 * @param userAndArtist the user and artist
	 */
	@PostMapping("/new")
	public void addUser(@RequestBody UserAndArtist userAndArtist) throws InvalidException, UnsupportedEncodingException {

		log.info("userandaartist : " + userAndArtist);
		try {
			userService.addNewUser(userAndArtist , ROLE_USER);

		} catch(InvalidException e){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}


}
