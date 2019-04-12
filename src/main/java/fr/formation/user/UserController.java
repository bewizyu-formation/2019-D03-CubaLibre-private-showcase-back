package fr.formation.user;


import fr.formation.controllers.AbstractController;
import fr.formation.user.exceptions.InvalidException;
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

	/**
	 * Signup.
	 *
	 * @param userDTO the user
	 */
	@PostMapping("/new")
	public void addUser(@RequestBody UserDTO userDTO) throws InvalidException, UnsupportedEncodingException {
		try {
			userService.addNewUser(userDTO, ROLE_USER);
		} catch(InvalidException e){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}


}
