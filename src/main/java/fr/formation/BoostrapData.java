package fr.formation;

import fr.formation.artist.Artist;
import fr.formation.departement_accepted.DepartementAcceptedService;
import fr.formation.security.SecurityConstants;
import fr.formation.user.User;
import fr.formation.user.UserService;
import fr.formation.artist.ArtistService;
import fr.formation.user.exceptions.InvalidPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class configure the dataset at application start
 */
@Component
public class BoostrapData {

	private UserService userService;

	private ArtistService artistService;

	private PasswordEncoder passwordEncoder;

	private DepartementAcceptedService departementAcceptedService;

	/**
	 * Instantiates a new Boostrap data.
	 *
	 * @param userService     the user service
	 * @param passwordEncoder the password encoder
	 */
	@Autowired
	public BoostrapData(UserService userService, PasswordEncoder passwordEncoder, ArtistService artistService, DepartementAcceptedService departementAcceptedService) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.artistService = artistService;
		this.departementAcceptedService = departementAcceptedService;
	}

	/**
	 * On start.
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void onStart() throws InvalidPasswordException {

		User admin = new User();

		admin.setUsername("admin");
		admin.setPassword("Admin888");
		admin.setEmail("admin@mail.com");
		admin.setCity("Nantes");

		userService.addNewUser(
				admin,
				SecurityConstants.ROLE_ADMIN
		);

		User user = new User();

		user.setUsername("user");
		user.setPassword("User8888");
		user.setEmail("user@mail.com");
		user.setCity("Lyon");
		userService.addNewUser(
				user,
				SecurityConstants.ROLE_USER
		);
		Artist artist69 = new Artist(
				"69 rue du test",
				"Artist",
				"description de l'artiste",
				"0102030405",
				"Artist.com",
				365,
				9,
				new byte[50]);
		artistService.addNewArtist(artist69);
		departementAcceptedService.addNewDepartementAcceptedService(69, artist69);

		Artist artist6944 = new Artist(
				"6944 rue du test",
				"Artist",
				"description de l'artiste",
				"0102030405",
				"Artist.com",
				365,
				9,
				new byte[50]);
		artistService.addNewArtist(artist6944);
		departementAcceptedService.addNewDepartementAcceptedService(69, artist6944);
		departementAcceptedService.addNewDepartementAcceptedService(44, artist6944);

		Artist artist44 = new Artist(
				"44 rue du test",
				"Artist",
				"description de l'artiste",
				"0102030405",
				"Artist.com",
				245324,
				8,
				new byte[50]);
		artistService.addNewArtist(artist44);
		departementAcceptedService.addNewDepartementAcceptedService(44, artist44);


	}

}
