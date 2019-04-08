package fr.formation.user;

import fr.formation.user.exceptions.InvalidPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;

	private UserRoleRepository userRoleRepository;

	private PasswordEncoder passwordEncoder;

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	/**
	 * Instantiates a new User service.
	 *
	 * @param userRepository     the user repository
	 * @param userRoleRepository the user role repository
	 */
	@Autowired
	public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userRoleRepository = userRoleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * transform a list of roles (as {@link String}) into a list of {@link GrantedAuthority}
	 *
	 * @param userRoles
	 *
	 * @return
	 */
	private static Collection<? extends GrantedAuthority> transformToAuthorities(List<String> userRoles) {
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

    public static Boolean validPassword(String password) {
        Pattern pattern = Pattern.compile("(?=.{8,}$)(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user != null) {
			List<String> roles = userRoleRepository.findRoleByUserName(username);
			return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
					transformToAuthorities(roles));
		} else {
			throw new UsernameNotFoundException("No user exists with username: " + username);
		}

	}

	public User getUserByUsername(String username){
		return userRepository.findByUsername(username);
	}

	/**
	 * Add a new user with the user repository
	 *
	 * @param user the user
	 * @param roles    the roles
	 */
	public void addNewUser(User user, String... roles) throws InvalidPasswordException{

		User userToAdd = new User();

		userToAdd.setUsername(user.getUsername());
		try{
			if(validPassword((user.getPassword()))){
				userToAdd.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			else {
				throw new InvalidPasswordException("Le mot de passe doit contenir au moins 8 " +
						"caractères dont une minuscule, une majuscule et un chiffre");
			}
		} catch(InvalidPasswordException e) {
			log.error(e.getMessage());
			return;
		}
		userToAdd.setPassword(passwordEncoder.encode(user.getPassword()));
		userToAdd.setEmail(user.getEmail());
		userToAdd.setCity(user.getCity());

		userToAdd = userRepository.save(userToAdd);

		for (String role : roles) {

			UserRole userRole = new UserRole();
			userRole.setRole(role);
			userRole.setUserId(userToAdd.getId());

			userRoleRepository.save(userRole);
		}

	}

}