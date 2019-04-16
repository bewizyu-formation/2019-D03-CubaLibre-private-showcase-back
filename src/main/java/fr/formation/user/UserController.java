package fr.formation.user;


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

	private static class Password{
		public String oldPassword;
		public String password;
		public String email;
	}

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

	@GetMapping("/{userName}")
	public UserAndArtist getUserAndArtist(@PathVariable String userName) throws UnsupportedEncodingException {
		UserAndArtist userAndArtist = new UserAndArtist();
		userAndArtist.setUser(userService.emptyPassword(userService.findByUsername(userName)));
		userAndArtist.setArtist(artistService.findByArtistName(userAndArtist.getUser().getArtistName()));
		return userAndArtist;
	}

	@GetMapping("/")
	public UserAndArtist getCurrentUserAndArtist() throws UnsupportedEncodingException {
		UserAndArtist userAndArtist = new UserAndArtist();
		userAndArtist.setUser(userService.emptyPassword(getAuthenticatedUserDTO()));
		userAndArtist.setArtist(artistService.findByArtistName(userAndArtist.getUser().getArtistName()));
		return userAndArtist;
	}

	@PutMapping("/changePassword")
	public void changePassword(@RequestBody Password password) throws InvalidException, UnsupportedEncodingException {
		try {
			UserDTO authenticatedUserDTO = getAuthenticatedUserDTO();
			if(userService.isSamePassword(authenticatedUserDTO.getPassword(),
					password.oldPassword) && authenticatedUserDTO.getEmail().equals(password.email)){
				authenticatedUserDTO.setPassword(password.password);
				userService.changeUserPassword(authenticatedUserDTO);
			}
		}catch(Exception e){
			System.out.println("Error");
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}

}
