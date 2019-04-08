package fr.formation.departement_accepted;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface DepartementAccepted repository.
 */
public interface DepartementAcceptedRepository extends JpaRepository<DepartementAccepted, Long> {
    /**
     * Find by artist name.
     *
     * @param code the code
     *
     * @return the DepartementAccepted
     */
    public List<DepartementAccepted> findByCode(int code);
}
