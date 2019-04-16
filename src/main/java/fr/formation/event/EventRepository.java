package fr.formation.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Event repository.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    public List<Event> findByArtistArtistName(String artistName);

}
