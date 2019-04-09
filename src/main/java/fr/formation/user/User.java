package fr.formation.user;

import fr.formation.artist.Artist;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * The type User.
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique=true)
	private String username;

	@NotNull
	private String password;

	@Email
	private String email;

	private String city;

	private String codeCity;

	private String codeDepartment;

	@OneToOne
	private Artist artist;

	public User(){

	}

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * Gets username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * Sets username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets city.
	 *
	 * @param city the city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets artist
	 *
	 * @return value of artist
	 */
	public Artist getArtist() {
		return artist;
	}

	/**
	 * Sets artist
	 *
	 * @param artist the artist
	 */
	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	/**
	 * Gets codeCity
	 *
	 * @return value of codeCity
	 */
	public String getCodeCity() {
		return codeCity;
	}

	/**
	 * Sets codeCity
	 *
	 * @param codeCity the codeCity
	 */
	public void setCodeCity(String codeCity) {
		this.codeCity = codeCity;
	}

	/**
	 * Gets codeDepartment
	 *
	 * @return value of codeDepartment
	 */
	public String getCodeDepartment() {
		return codeDepartment;
	}

	/**
	 * Sets codeDepartment
	 *
	 * @param codeDepartment the codeDepartment
	 */
	public void setCodeDepartment(String codeDepartment) {
		this.codeDepartment = codeDepartment;
	}
}
