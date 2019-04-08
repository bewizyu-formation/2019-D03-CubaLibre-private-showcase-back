package fr.formation.departement_accepted;

import fr.formation.artist.Artist;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * The type DepartementAccepted.
 */
@Entity
@Table(name = "departement_accepted")
public class DepartementAccepted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "code")
    private int code;

    @OneToOne
    private Artist artist;

    /**
     * Gets id
     *
     * @return value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets code
     *
     * @return value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
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
