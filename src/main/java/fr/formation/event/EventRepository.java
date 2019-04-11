package fr.formation.event;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Event repository.
 */
public interface EventRepository extends JpaRepository<Event, Long> {

}
