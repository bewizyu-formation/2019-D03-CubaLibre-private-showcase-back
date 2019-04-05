package fr.formation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

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
	 * @param username the username
	 * @param password the password
	 * @param email the email
	 * @param city the city
	 * @param roles    the roles
	 */
	@PutMapping("/")
	public void signup(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String city, @RequestParam String... roles) {

		userService.addNewUser(username, password, email, city, roles);

	}



}
