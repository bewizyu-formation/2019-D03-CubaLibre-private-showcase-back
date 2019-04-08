package fr.formation.user;

import fr.formation.user.exceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import static fr.formation.security.SecurityConstants.ROLE_USER;



import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;


/**
 * The type User controller.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Signup.
	 *
	 * @param user the user
	 */
	@PutMapping("/")
	public void signup(@RequestBody User user) throws InvalidPasswordException {
	    try {
            userService.addNewUser(user, ROLE_USER);
        } catch(InvalidPasswordException e){

        }
	}

}
