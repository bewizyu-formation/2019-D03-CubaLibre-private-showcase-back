package fr.formation.departement_accepted;

import javax.persistence.*;

/**
 * The type DepartementAccepted.
 */
@Entity
@Table(name = "departement_accepted")
public class DepartementAccepted {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "code")
    private String code;





    /**
     * Gets id
     *
     * @return value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets code
     *
     * @return value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }
}
