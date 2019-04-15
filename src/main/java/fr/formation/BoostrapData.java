package fr.formation;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistDTO;
import fr.formation.county_accepted.CountyAcceptedService;
import fr.formation.security.SecurityConstants;
import fr.formation.user.User;
import fr.formation.user.UserAndArtist;
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

		UserDTO admin = new UserDTO();

		admin.setUsername("admin");
		admin.setPassword("adminPass1");
		admin.setEmail("admin@mail.com");
		admin.setCity("Nantes");

		UserDTO user = new UserDTO();

		user.setUsername("user");
		user.setPassword("userPass1");
		user.setEmail("user@mail.com");
		user.setCity("Lyon");
		user.setArtistName("Artist1");

		ArtistDTO artist69 = new ArtistDTO();

		artist69.setArtistName("Artist1");
		artist69.setLongDescription("description longue très longue de l'artiste");
		artist69.setShortDescription("description courte de l'artiste");
		artist69.setWebsite("Artist.com");
		artist69.setPhone("0102030405");
		artist69.setAddress("69 rue du test");
		artist69.setVoteNumber(365);
		artist69.setRating(9);
		artist69.setPicture(new byte[50]);

		UserAndArtist adminWithoutArtist = new UserAndArtist(admin, null);
		UserAndArtist userAndArtist69 = new UserAndArtist(user, artist69);

		userService.addNewUser(
				adminWithoutArtist,
				SecurityConstants.ROLE_ADMIN
		);
		userService.addNewUser(
				userAndArtist69,
				SecurityConstants.ROLE_USER
		);




		ArtistDTO artist6944 = new ArtistDTO(
				"Artist2",
				"description courte de l'artiste",
				"description longue très longue de l'artiste",
				365,
				9,
				"Artist.com",
				"0102030405",
				"6944 rue du test",
				new byte[50]);
		artistService.addNewArtist(artist6944);
		countyAcceptedService.addCountyAccepted(69, artistService.findByArtistName("Artist2"));
		countyAcceptedService.addCountyAccepted(44, artistService.findByArtistName("Artist2"));

		/*Artist artist44 = new Artist(
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
		countyAcceptedService.addCountyAccepted(44, artist44);*/


	}

}
