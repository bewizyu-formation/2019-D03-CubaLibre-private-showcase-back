package fr.formation;

import fr.formation.artist.Artist;
import fr.formation.county_accepted.CountyAcceptedService;
import fr.formation.security.SecurityConstants;
import fr.formation.user.User;
import fr.formation.user.UserDTO;
import fr.formation.user.UserService;
import fr.formation.user.exceptions.InvalidException;
import fr.formation.artist.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * This class configure the dataset at application start
 */
@Component
public class BoostrapData {

	private UserService userService;

	private ArtistService artistService;

	private PasswordEncoder passwordEncoder;

	private CountyAcceptedService countyAcceptedService;

	/**
	 * Instantiates a new Boostrap data.
	 *
	 * @param userService     the user service
	 * @param passwordEncoder the password encoder
	 */
	@Autowired
	public BoostrapData(UserService userService, PasswordEncoder passwordEncoder, ArtistService artistService, CountyAcceptedService countyAcceptedService) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.artistService = artistService;
		this.countyAcceptedService = countyAcceptedService;
	}

	/**
	 * On start.
	 */
	@EventListener(ContextRefreshedEvent.class)
	public void onStart() throws InvalidException, UnsupportedEncodingException {

		/*UserDTO admin = new UserDTO();

		admin.setUsername("admin");
		admin.setPassword("adminPass1");
		admin.setEmail("admin@mail.com");
		admin.setCity("Nantes");


		userService.addNewUser(
				admin,
				SecurityConstants.ROLE_ADMIN
		);

		UserDTO user = new UserDTO();

		user.setUsername("user");
		user.setPassword("userPass1");
		user.setEmail("user@mail.com");
		user.setCity("Lyon");
		user.setArtistName("Artist1");

		userService.addNewUser(
				user,
				SecurityConstants.ROLE_USER
		);

		Artist artist69 = new Artist(
				"Artist1",
				"description longue très longue de l'artiste",
				"description courte de l'artiste",
				"Artist.com ",
				"0102030405",
				"69 rue du test",
				365,
				9,
				new byte[50]);
		artistService.addNewArtist(artist69);
		//countyAcceptedService.addCountyAccepted(69, artist69);

		/*Artist artist6944 = new Artist(
				"Artist2",
				"description longue très longue de l'artiste",
				"description courte de l'artiste",
				"Artist.com",
				"0102030405",
				"6944 rue du test",
				365,
				9,
				new byte[50]);
		artistService.addNewArtist(artist6944);
		countyAcceptedService.addCountyAccepted(69, artist6944);
		countyAcceptedService.addCountyAccepted(44, artist6944);

		Artist artist44 = new Artist(
				"Artist3",
				"description longue très longue de l'artiste",
				"description courte de l'artiste",
				"Artist.com",
				"0102030405",
				"44 rue du test ",
				245324,
				8,
				new byte[50]);
		artistService.addNewArtist(artist44);
		//countyAcceptedService.addCountyAccepted(44, artist44);*/


	}

}
