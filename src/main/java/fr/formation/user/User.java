package fr.formation.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.formation.artist.Artist;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The type User.
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "username")
	private String username;

	//@Size(min=8)
	//@Pattern(regexp="(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)")
	@NotNull
	@Column(name = "password")
	private String password;

	@Email
	@Column(name = "email")
	private String email;


	@Column(name = "city")
	private String city;

	@OneToOne
	private Artist artist;


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
}
