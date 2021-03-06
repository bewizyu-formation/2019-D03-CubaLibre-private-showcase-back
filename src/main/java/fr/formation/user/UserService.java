package fr.formation.user;


import fr.formation.artist.ArtistDTO;
import fr.formation.artist.ArtistRepository;
import fr.formation.artist.ArtistService;
import fr.formation.county_accepted.CountyAcceptedService;
import fr.formation.event.EventRepository;
import fr.formation.geo.services.impl.CommuneServiceImpl;
import fr.formation.geo.services.impl.DepartementServiceImpl;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommuneServiceImpl communeServiceImpl;

    @Autowired
    private DepartementServiceImpl departementServiceImpl;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private CountyAcceptedService countyAcceptedService;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    /*
    We didn't touch these two methods,they have to to with security
     */
    private static Collection<? extends GrantedAuthority> transformToAuthorities(List<String> userRoles) {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
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

    /*
    Utility methods
     */

    public static Boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("(?=.{8,}$)(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public UserDTO encryptPassword(UserDTO userDTO) {

        UserDTO passwordEncryptedUserDTO = new UserDTO();

        passwordEncryptedUserDTO.setId(userDTO.getId());
        passwordEncryptedUserDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        passwordEncryptedUserDTO.setUsername(userDTO.getUsername());
        passwordEncryptedUserDTO.setEventIdInvitatedList(userDTO.getEventIdInvitatedList());
        passwordEncryptedUserDTO.setEventIdConfirmedList(userDTO.getEventIdConfirmedList());
        passwordEncryptedUserDTO.setEmail(userDTO.getEmail());
        passwordEncryptedUserDTO.setArtistName(userDTO.getArtistName());
        passwordEncryptedUserDTO.setCity(userDTO.getCity());

        return passwordEncryptedUserDTO;
    }

    public UserDTO emptyPassword(UserDTO userDTO) {

        UserDTO passwordEncryptedUserDTO = new UserDTO();

        passwordEncryptedUserDTO.setId(userDTO.getId());
        passwordEncryptedUserDTO.setPassword("");
        passwordEncryptedUserDTO.setUsername(userDTO.getUsername());
        passwordEncryptedUserDTO.setEventIdInvitatedList(userDTO.getEventIdInvitatedList());
        passwordEncryptedUserDTO.setEventIdConfirmedList(userDTO.getEventIdConfirmedList());
        passwordEncryptedUserDTO.setEmail(userDTO.getEmail());
        passwordEncryptedUserDTO.setArtistName(userDTO.getArtistName());
        passwordEncryptedUserDTO.setCity(userDTO.getCity());

        return passwordEncryptedUserDTO;
    }

    private boolean cityExists(String city) throws UnsupportedEncodingException {
        return communeServiceImpl.getCommunes(city)
                .stream()
                .map(c -> ((String) c.get("nom")).equalsIgnoreCase(city))
                .reduce((a, b) -> a || b)
                .isPresent();
    }

    public boolean isSamePassword(String oldPasswordDataBase, String oldPasswordUser) {
        return this.passwordEncoder.matches(oldPasswordUser, oldPasswordDataBase);
    }

    /*
    Methods that consult/modify the database
     */

    public UserDTO findByUsername(String username) {
        return createUserDTO(userRepository.findByUsername(username));
    }

    public String findCodeCounty(UserDTO userDTO) throws UnsupportedEncodingException {
        return (String) communeServiceImpl.getCommunes(userDTO.getCity())
                .stream()
                .filter(c -> ((String) c.get("nom")).equalsIgnoreCase(userDTO.getCity()))
                .collect(Collectors.toList())
                .get(0)
                .get("codeDepartement");
    }

    public String findCodeCity(UserDTO userDTO) throws UnsupportedEncodingException {
        return (String) communeServiceImpl.getCommunes(userDTO.getCity())
                .stream()
                .filter(c -> ((String) c.get("nom")).equalsIgnoreCase(userDTO.getCity()))
                .collect(Collectors.toList())
                .get(0)
                .get("code");
    }

    public UserDTO findByArtistName(String artistName) {
        return createUserDTO(userRepository.findByArtistArtistName(artistName));
    }

    public List<UserDTO> findByCountyCode(int code) {
        return userRepository.findByCodeCounty("" + code)
                .stream()
                .map(user -> createUserDTO(user))
                .collect(Collectors.toList());
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void changeUserPassword(UserDTO userDTO) throws InvalidException, UnsupportedEncodingException {
        if (isValidPassword((userDTO.getPassword()))) {
            saveUser(createUser(encryptPassword(userDTO)));
        } else {
            throw new InvalidPasswordException();
        }
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

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        } else if (!isValidPassword((userDTO.getPassword()))) {
            throw new InvalidPasswordException();
        } else if (!cityExists(userDTO.getCity())) {
            throw new InvalidCityException();
        } else {
            //On créé un artist d'abord si il existe
            if (artistDTO != null) {
                if (artistRepository.findByArtistName(artistDTO.getArtistName()) != null) {
                    throw new ArtistAlreadyExistsException();
                } else {
                    artistService.saveArtist(artistDTO);
                }
            }

            saveUser(createUser(encryptPassword(userDTO)));

            //On ne peut rajouter le département que une fois que l'utilisateur et l'artiste ont été créés
            if (artistDTO != null) {
                countyAcceptedService.addCountyAccepted(
                        Integer.parseInt(findCodeCounty(userDTO)),
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


    /*
    Methods to convert User to UserDTO and UserDTO to User
     */


    public UserDTO createUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
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

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setCity(userDTO.getCity());

        user.setCodeCity(findCodeCity(userDTO));

        user.setCodeCounty(findCodeCounty(userDTO));

        if (userDTO.getEventIdInvitatedList() != null) {
            user.setEventInvitatedList(
                    userDTO.getEventIdInvitatedList()
                            .stream()
                            .map(id -> eventRepository.findById(id).get())
                            .collect(Collectors.toSet())
            );
        }

        if (userDTO.getEventIdConfirmedList() != null) {
            user.setEventConfirmedList(
                    userDTO.getEventIdConfirmedList()
                            .stream()
                            .map(id -> eventRepository.findById(id).get())
                            .collect(Collectors.toSet())
            );
        }

        if (artistRepository.findByArtistName(userDTO.getArtistName()) != null) {
            user.setArtist(artistRepository.findByArtistName(userDTO.getArtistName()));
        }

        return user;
    }

}
