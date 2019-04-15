package fr.formation.user;


import fr.formation.artist.Artist;
import fr.formation.artist.ArtistDTO;
import fr.formation.artist.ArtistRepository;
import fr.formation.artist.ArtistService;
import fr.formation.county_accepted.CountyAcceptedService;
import fr.formation.event.EventService;
import fr.formation.geo.services.impl.CommuneServiceImpl;
import fr.formation.user.exceptions.*;
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

    private ArtistRepository artistRepository;

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
                       ArtistRepository artistRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder,
                       CommuneServiceImpl communeServiceImpl,
                       ArtistService artistService,
                       EventService eventService,
                       CountyAcceptedService countyAcceptedService) {
        this.userRepository = userRepository;
        this.artistRepository = artistRepository;
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
        return communeServiceImpl.getCommunes(city)
                .stream()
                .map(c -> ((String) c.get("nom")).equalsIgnoreCase(city))
                .reduce((a, b) -> a || b)
                .isPresent();
    }

    private UserDTO encryptPassword(UserDTO userDTO) {

        UserDTO passwordEncryptedUserDTO = new UserDTO();

        passwordEncryptedUserDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        passwordEncryptedUserDTO.setUsername(userDTO.getUsername());
        passwordEncryptedUserDTO.setEventIdInvitatedList(userDTO.getEventIdInvitatedList());
        passwordEncryptedUserDTO.setEventIdConfirmedList(userDTO.getEventIdConfirmedList());
        passwordEncryptedUserDTO.setEmail(userDTO.getEmail());
        passwordEncryptedUserDTO.setArtistName(userDTO.getArtistName());
        passwordEncryptedUserDTO.setCity(userDTO.getCity());

        return passwordEncryptedUserDTO;
    }


    /**
     * Add a new user with the user repository
     *
     * @param userAndArtist the user and artist if there
     * @param roles         the roles
     */
    public void addNewUser(UserAndArtist userAndArtist, String... roles) throws InvalidException, UnsupportedEncodingException {

        UserDTO userDTO = userAndArtist.getUser();
        ArtistDTO artistDTO = userAndArtist.getArtist();

        log.info("user : " + userDTO);

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        } else if (!isValidPassword((userDTO.getPassword()))) {
            throw new InvalidPasswordException();
        } else if (!cityExists(userDTO.getCity())) {
            throw new InvalidCityException();
        } else {
            //On créé un artist d'abord si il existe
            if (artistDTO != null) {
                if(artistRepository.findByArtistName(artistDTO.getArtistName()) != null){
                    throw new ArtistAlreadyExistsException();
                } else {
                    artistService.addNewArtist(artistDTO);
                }
            }

            userRepository.save(createUser(encryptPassword(userDTO)));

            //On ne peut rajouter le département que une fois que l'utilisateur et l'artiste ont été créés
            if (artistDTO != null) {
                countyAcceptedService.addCountyAccepted(
                        Integer.parseInt(getUserByUsername(userDTO.getUsername()).getCodeCounty()),
                        artistRepository.findByArtistName(userDTO.getArtistName()));
            }
        }

        for (String role : roles) {

            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUserId(userRepository.findByUsername(userDTO.getUsername()).getId());

            userRoleRepository.save(userRole);
        }
    }

    public User findByArtistName(String artistName) {
        return userRepository.findByArtist(artistRepository.findByArtistName(artistName));
    }

    public UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setCity(user.getCity());

        if (user.getArtist() != null) {
            userDTO.setArtistName(user.getArtist().getArtistName());
        }

        if (user.getEventConfirmedList() != null) {
            userDTO.setEventIdConfirmedList(
                    user.getEventConfirmedList()
                            .stream()
                            .map(e -> e.getId())
                            .collect(Collectors.toSet())
            );
        }

        if (user.getEventInvitatedList() != null) {
            userDTO.setEventIdInvitatedList(
                    user.getEventConfirmedList()
                            .stream()
                            .map(e -> e.getId())
                            .collect(Collectors.toSet())
            );
        }

        return userDTO;
    }

    public User createUser(UserDTO userDTO) throws UnsupportedEncodingException {
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

        if (userDTO.getEventIdInvitatedList() != null) {
            user.setEventInvitatedList(
                    userDTO.getEventIdInvitatedList()
                            .stream()
                            .map(id -> eventService.findById(id))
                            .collect(Collectors.toSet())
            );
        }

        if (userDTO.getEventIdConfirmedList() != null) {
            user.setEventConfirmedList(
                    userDTO.getEventIdConfirmedList()
                            .stream()
                            .map(id -> eventService.findById(id))
                            .collect(Collectors.toSet())
            );
        }

        if (artistRepository.findByArtistName(userDTO.getArtistName()) != null) {
            user.setArtist(artistRepository.findByArtistName(userDTO.getArtistName()));
        }

        return user;
    }
}