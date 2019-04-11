package fr.formation.county_accepted;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface CountyAccepted repository.
 */
public interface CountyAcceptedRepository extends JpaRepository<CountyAccepted, Long> {
    /**
     * Find by artist name.
     *
     * @param code the code
     *
     * @return the CountyAccepted
     */
    public List<CountyAccepted> findByCode(int code);
}
