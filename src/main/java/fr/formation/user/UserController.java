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
	 * @param user the user
	 */
	@PostMapping("/")
	public void signup( User user) throws InvalidException, UnsupportedEncodingException {
		try {
			userService.addNewUser(user, ROLE_USER);
		} catch(InvalidException e){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@PostMapping("/changePassword")
	public void changePassword(@RequestBody String []fields) throws InvalidException, UnsupportedEncodingException {
		try {
			if(getAuthenticatedUser().getPassword().equals(this.userService.passwordEncode(fields[0])) && getAuthenticatedUser().getEmail().equals(fields[2])){
				System.out.println("Password changed");
				getAuthenticatedUser().setPassword(this.userService.passwordEncode(fields[1]));
				User user = userService.getUserByUsername(getAuthenticatedUser().getUsername());
				user.setPassword(this.userService.passwordEncode(fields[1]));
			}
		}catch(Exception e){
			System.out.println("Error");
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}

}
