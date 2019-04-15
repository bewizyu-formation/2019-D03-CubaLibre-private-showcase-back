package fr.formation.user;


import fr.formation.controllers.AbstractController;
import fr.formation.user.exceptions.InvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.io.File;
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
	public void signup(@RequestBody User user) throws InvalidException, UnsupportedEncodingException {

		try {
			userService.addNewUser(user, ROLE_USER);
		} catch(InvalidException e){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	/**
	 * @param fields[0] --> oldPassword
	 * @param fields[1] --> password
	 * @param fields[2] --> email
	 * */
	@PutMapping("/changePassword")
	public void changePassword(@RequestBody String []fields) throws InvalidException, UnsupportedEncodingException {
		try {
			User user = getAuthenticatedUser();
			if(userService.isSamePassword(user.getPassword(), fields[0]) && user.getEmail().equals(fields[2])){
				user.setPassword(this.userService.passwordEncode(fields[1]));
				userService.saveUser(user);
			}
		}catch(Exception e){
			System.out.println("Error");
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}

}
