package fr.formation;

import fr.formation.artist.ArtistDTO;
import fr.formation.artist.ArtistRepository;
import fr.formation.county_accepted.CountyAcceptedService;
import fr.formation.security.SecurityConstants;
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

	private ArtistRepository artistRepository;

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
	public BoostrapData(ArtistRepository artistRepository, UserService userService, PasswordEncoder passwordEncoder, ArtistService artistService, CountyAcceptedService countyAcceptedService) {
		this.artistRepository = artistRepository;
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
		admin.setCity("Paris");

		UserAndArtist adminWithoutArtist = new UserAndArtist(admin, null);

		userService.addNewUser(
				adminWithoutArtist,
				SecurityConstants.ROLE_ADMIN
		);

		////////////////////////////////////////////////////////////////////////////

		UserDTO user1 = new UserDTO();

		user1.setUsername("user1");
		user1.setPassword("userPass1");
		user1.setEmail("user1@mail.com");
		user1.setCity("Paris");
		user1.setArtistName("artist1");

		ArtistDTO artist1 = new ArtistDTO();

		artist1.setArtistName("artist1");
		artist1.setLongDescription("description longue très longue de l'artiste1");
		artist1.setShortDescription("description courte de l'artiste1");
		artist1.setWebsite("Artist1.com");
		artist1.setPhone("0101010101");
		artist1.setAddress("1 rue du test");
		artist1.setVoteNumber(111111);
		artist1.setRating(1);

		UserAndArtist userAndArtist1 = new UserAndArtist(user1, artist1);

		userService.addNewUser(
				userAndArtist1,
				SecurityConstants.ROLE_USER
		);

		////////////////////////////////////////////////////////////////////////////

		UserDTO user2 = new UserDTO();

		user2.setUsername("user2");
		user2.setPassword("userPass2");
		user2.setEmail("user2@mail.com");
		user2.setCity("Paris");
		user2.setArtistName("artist2");

		ArtistDTO artist2 = new ArtistDTO();

		artist2.setArtistName("artist2");
		artist2.setLongDescription("description longue très longue de l'artiste2");
		artist2.setShortDescription("description courte de l'artiste2");
		artist2.setWebsite("Artist2.com");
		artist2.setPhone("0202020202");
		artist2.setAddress("2 rue du test");
		artist2.setVoteNumber(222222);
		artist2.setRating(2);

		UserAndArtist userAndArtist2 = new UserAndArtist(user2, artist2);

		userService.addNewUser(
				userAndArtist2,
				SecurityConstants.ROLE_USER
		);

		countyAcceptedService.addCountyAccepted(44, artistRepository.findByArtistName("artist2"));

		////////////////////////////////////////////////////////////////////////////

		UserDTO user3 = new UserDTO();

		user3.setUsername("user3");
		user3.setPassword("userPass3");
		user3.setEmail("user3@mail.com");
		user3.setCity("Nantes");
		user3.setArtistName("artist3");

		ArtistDTO artist3 = new ArtistDTO();

		artist3.setArtistName("artist3");
		artist3.setLongDescription("description longue très longue de l'artiste3");
		artist3.setShortDescription("description courte de l'artiste3");
		artist3.setWebsite("Artist3.com");
		artist3.setPhone("0303030303");
		artist3.setAddress("3 rue du test");
		artist3.setVoteNumber(333333);
		artist3.setRating(3);

		UserAndArtist userAndArtist3 = new UserAndArtist(user3, artist3);

		userService.addNewUser(
				userAndArtist3,
				SecurityConstants.ROLE_USER
		);

		countyAcceptedService.addCountyAccepted(69, artistRepository.findByArtistName("artist3"));
		countyAcceptedService.addCountyAccepted(75, artistRepository.findByArtistName("artist3"));


		////////////////////////////////////////////////////////////////////////////

		UserDTO user4 = new UserDTO();

		user4.setUsername("user4");
		user4.setPassword("userPass4");
		user4.setEmail("user4@mail.com");
		user4.setCity("Lyon");
		user4.setArtistName("artist4");

		ArtistDTO artist4 = new ArtistDTO();

		artist4.setArtistName("artist4");
		artist4.setLongDescription("description longue très longue de l'artiste4");
		artist4.setShortDescription("description courte de l'artiste4");
		artist4.setWebsite("Artist4.com");
		artist4.setPhone("0404040404");
		artist4.setAddress("4 rue du test");
		artist4.setVoteNumber(444444);
		artist4.setRating(4);

		UserAndArtist userAndArtist4 = new UserAndArtist(user4, artist4);

		userService.addNewUser(
				userAndArtist4,
				SecurityConstants.ROLE_USER
		);

		countyAcceptedService.addCountyAccepted(69, artistRepository.findByArtistName("artist4"));


	}

}
