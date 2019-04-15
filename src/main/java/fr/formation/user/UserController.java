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
<<<<<<< HEAD
	public void signup(@RequestBody User user) throws InvalidException, UnsupportedEncodingException {

=======
	public void signup( User user) throws InvalidException, UnsupportedEncodingException {
>>>>>>> b98a84fc5570c3352995d3363fd8aa2771df4b80
		try {
			userService.addNewUser(user, ROLE_USER);
		} catch(InvalidException e){
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

<<<<<<< HEAD
	@PutMapping("/changePassword/")
	public void changePassword(@RequestBody String oldPassword, String newPassword, String email) throws InvalidException, UnsupportedEncodingException {
		try {
			if(getAuthenticatedUser().getPassword().equals(this.userService.passwordEncode(oldPassword)) && getAuthenticatedUser().getEmail().equals(email)){
				getAuthenticatedUser().setPassword(this.userService.passwordEncode(newPassword));
			}
		}catch(Exception e){
=======
	/**
	 * @param fields[0] --> oldPassword
	 * @param fields[1] --> password
	 * @param fields[2] --> email
	 * */
	@PutMapping("/changePassword")
	public void changePassword(@RequestBody String []fields) throws InvalidException, UnsupportedEncodingException {
		try {
			if(userService.isSamePassword(getAuthenticatedUser().getPassword(), fields[0]) && getAuthenticatedUser().getEmail().equals(fields[2])){
				getAuthenticatedUser().setPassword(this.userService.passwordEncode(fields[1]));
				User user = userService.getUserByUsername(getAuthenticatedUser().getUsername());
				user.setPassword(this.userService.passwordEncode(fields[1]));
			}
		}catch(Exception e){
			System.out.println("Error");
>>>>>>> b98a84fc5570c3352995d3363fd8aa2771df4b80
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}

	}

}
