package fr.formation.user;


import fr.formation.controllers.AbstractController;
import fr.formation.user.exceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


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
	@PutMapping("/")
	public void signup(@RequestBody User user) throws InvalidPasswordException {
	    try {
            userService.addNewUser(user, ROLE_USER);
        } catch(InvalidPasswordException e){

        }
	}

}
