package fr.formation.user;


import fr.formation.artist.ArtistService;
import fr.formation.county_accepted.CountyAcceptedService;
import fr.formation.geo.services.impl.CommuneServiceImpl;
import fr.formation.user.exceptions.InvalidCityException;
import fr.formation.user.exceptions.InvalidException;
import fr.formation.user.exceptions.InvalidPasswordException;
import fr.formation.user.exceptions.UserAlreadyExistsException;
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

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.LinkedHashMap;
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

    private CommuneServiceImpl communeServiceImpl;

    private ArtistService artistService;

    private CountyAcceptedService countyAcceptedService;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /**
     * Instantiates a new User service.
     *
     * @param userRepository     the user repository
     * @param userRoleRepository the user role repository
     */
    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, CommuneServiceImpl communeServiceImpl, ArtistService artistService, CountyAcceptedService countyAcceptedService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.communeServiceImpl = communeServiceImpl;
        this.countyAcceptedService = countyAcceptedService;
        this.artistService = artistService;

    }

    /**
     * transform a list of roles (as {@link String}) into a list of {@link GrantedAuthority}
     *
     * @param userRoles
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

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    /**
     * Add a new user with the user repository
     *
     * @param user  the user
     * @param roles the roles
     */
    public void addNewUser(User user, String... roles) throws InvalidException, UnsupportedEncodingException {
        User userToAdd = new User();
        if (userRepository.findByUsername(user.getUsername()) == null) {
            userToAdd.setUsername(user.getUsername());
        } else {
            throw new UserAlreadyExistsException();
        }

		if (validPassword((user.getPassword()))) {
			userToAdd.setPassword(passwordEncoder.encode(user.getPassword()));
		} else {
			throw new InvalidPasswordException();
		}

		userToAdd.setEmail(user.getEmail());


		List<LinkedHashMap> cities = communeServiceImpl.getCommunes(user.getCity());
		boolean cityExist = false;
		for (LinkedHashMap<String, String> city : cities) {
			if(city.get("nom").equalsIgnoreCase(user.getCity())){
				userToAdd.setCodeCounty(city.get("codeDepartement"));
				userToAdd.setCodeCity(city.get("code"));
                userToAdd.setCity(user.getCity());
				cityExist = true;
			}
		}
		if (!cityExist) {
			throw new InvalidCityException();
		}

		if(user.getArtist()!=null && cityExist){
			artistService.addNewArtist(user.getArtist());
			countyAcceptedService.addNewCountyAccepted(Integer.parseInt(userToAdd.getCodeCounty()), user.getArtist());
		}


		userToAdd = userRepository.save(userToAdd);

		for (String role : roles) {

		    UserRole userRole = new UserRole();
		    userRole.setRole(role);
		    userRole.setUserId(userToAdd.getId());

		    userRoleRepository.save(userRole);
		}


    }

    public String passwordEncode(String password){
        return this.passwordEncoder.encode(password);
    }
}
