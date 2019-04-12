package fr.formation.user;


import fr.formation.artist.ArtistService;
import fr.formation.county_accepted.CountyAcceptedService;
import fr.formation.event.EventService;
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
import java.util.stream.Collectors;

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

    private EventService eventService;

    private CountyAcceptedService countyAcceptedService;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /**
     * Instantiates a new User service.
     *
     * @param userRepository     the user repository
     * @param userRoleRepository the user role repository
     */
    @Autowired
    public UserService(UserRepository userRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder,
                       CommuneServiceImpl communeServiceImpl,
                       ArtistService artistService,
                       EventService eventService,
                       CountyAcceptedService countyAcceptedService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.communeServiceImpl = communeServiceImpl;
        this.countyAcceptedService = countyAcceptedService;
        this.artistService = artistService;
        this.eventService = eventService;

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

    public static Boolean isValidPassword(String password) {
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

    private boolean cityExists(String city) throws UnsupportedEncodingException {
        return   communeServiceImpl.getCommunes(city)
                .stream()
                .map(c -> ((String) c.get("nom")).equalsIgnoreCase(city))
                .reduce((a, b) -> a || b)
                .isPresent();
    }


    /**
     * Add a new user with the user repository
     *  @param userDTO  the user
     * @param roles the roles
     */
    public void addNewUser(UserDTO userDTO, String... roles) throws InvalidException, UnsupportedEncodingException {


        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        } else if (!isValidPassword((userDTO.getPassword()))){
            throw new InvalidPasswordException();
        } else if (!cityExists(userDTO.getCity())) {
            throw new InvalidCityException();
        } else {
            userRepository.save(createUser(userDTO));
            if (userDTO.getArtistId())
        }




        List<LinkedHashMap> cities = communeServiceImpl.getCommunes(userDTO.getCity());
        boolean cityExist = false;
        for (LinkedHashMap<String, String> city : cities) {
            if (city.get("nom").equalsIgnoreCase(userDTO.getCity())) {
                userToAdd.setCodeCounty(city.get("codeDepartement"));
                userToAdd.setCodeCity(city.get("code"));
                userToAdd.setCity(userDTO.getCity());
                cityExist = true;
            }
        }
        if (!cityExist) {
            throw new InvalidCityException();
        }

        if (userDTO.getArtist() != null && cityExist) {
            artistService.addNewArtist(userDTO.getArtist());
            countyAcceptedService.addNewCountyAccepted(Integer.parseInt(userToAdd.getCodeCounty()), userDTO.getArtist());
        }


        userToAdd = userRepository.save(userToAdd);

        for (String role : roles) {

            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUserId(userToAdd.getId());

            userRoleRepository.save(userRole);
        }


    }

    public UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setArtistId(user.getArtist().getId());
        userDTO.setCity(user.getCity());
        userDTO.setEmail(user.getEmail());
        userDTO.setEventIdConfirmedList(
                user.getEventConfirmedList()
                        .stream()
                        .map(e -> e.getId())
                        .collect(Collectors.toSet())
        );
        userDTO.setEventIdInvitatedList(
                user.getEventConfirmedList()
                .stream()
                .map(e -> e.getId())
                .collect(Collectors.toSet())
        );
        userDTO.setUsername(user.getEmail());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

    public User createUser(UserDTO userDTO) throws UnsupportedEncodingException{
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setCity(userDTO.getCity());

        user.setCodeCity(
                (String) communeServiceImpl.getCommunes(userDTO.getCity())
                .stream()
                .filter(c -> ((String) c.get("nom")).equalsIgnoreCase(userDTO.getCity()))
                .collect(Collectors.toList())
                .get(0)
                .get("code")
        );

        user.setCodeCounty(
                (String) communeServiceImpl.getCommunes(userDTO.getCity())
                .stream()
                .filter(c -> ((String) c.get("nom")).equalsIgnoreCase(userDTO.getCity()))
                .collect(Collectors.toList())
                .get(0)
                .get("codeDepartement"));

        user.setEventInvitatedList(
                userDTO.getEventIdInvitatedList()
                .stream()
                .map(id -> eventService.findById(id))
                .collect(Collectors.toSet())
        );

        user.setEventConfirmedList(
                userDTO.getEventIdConfirmedList()
                .stream()
                .map(id -> eventService.findById(id))
                .collect(Collectors.toSet())
        );

        if (artistService.findById(userDTO.getArtistId()) != null) {
            user.setArtist(artistService.findById(userDTO.getArtistId()));
        }

        return user;
    }
}