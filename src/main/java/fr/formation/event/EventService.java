package fr.formation.event;

import fr.formation.artist.ArtistRepository;
import fr.formation.user.UserDTO;
import fr.formation.user.UserRepository;
import fr.formation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private UserService userService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserRepository userRepository;

    public Event findById(Long id) {
        return eventRepository.findById(id).get();
    }

    public void addNewEvent(EventDTO eventDTO) {
        //Recuperer l'user authentifié
        String organiserUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO organiser = userService.findByUsername(organiserUsername);

        //ajouter tous les users du departements dans invitatedList

        eventRepository.save(createEvent(eventDTO));
    }

    public List<EventDTO> findByArtistName(String artistName) {
        return eventRepository.findByArtistArtistName(artistName)
                .stream()
                .map(e -> createEventDTO(e))
                .collect(Collectors.toList());
    }

    public List<EventDTO> findAll() {
        return eventRepository.findAll()
                .stream()
                .map(e -> createEventDTO(e))
                .collect(Collectors.toList());
    }

    public void saveEvent(EventDTO eventDTO) {
        eventRepository.save(createEvent(eventDTO));
    }

    public Event createEvent(EventDTO eventDTO) {

        Event event = new Event();

        event.setDate(eventDTO.getDate());
        event.setArtist(artistRepository.findByArtistName(eventDTO.getArtistName()));

        if (eventDTO.getConfirmedUsernameList() != null) {
            event.setConfirmedUserList(
                    eventDTO.getConfirmedUsernameList()
                            .stream()
                            .map(username -> userRepository.findByUsername(username))
                            .collect(Collectors.toSet())
            );
        }

        if (eventDTO.getInvitatedUsernameList() != null) {
            event.setInvitatedUserList(
                    eventDTO.getInvitatedUsernameList()
                            .stream()
                            .map(username -> userRepository.findByUsername(username))
                            .collect(Collectors.toSet())
            );
        }

        event.setOrganiser(userRepository.findByUsername(eventDTO.getOrganiserUsername()));

        event.setMaxConfirmed(eventDTO.getMaxConfirmed());

        return event;
    }

    public EventDTO createEventDTO(Event event) {

        EventDTO eventDTO = new EventDTO();

        eventDTO.setDate(event.getDate());
        eventDTO.setArtistName(event.getArtist().getArtistName());

        if (event.getConfirmedUserList() != null) {
            eventDTO.setConfirmedUsernameList(
                    event.getConfirmedUserList()
                            .stream()
                            .map(user -> user.getUsername())
                            .collect(Collectors.toSet())
            );
        }

        if (event.getInvitatedUserList() != null) {
            eventDTO.setInvitatedUsernameList(
                    event.getInvitatedUserList()
                            .stream()
                            .map(user -> user.getUsername())
                            .collect(Collectors.toSet())
            );
        }

        eventDTO.setOrganiserUsername(event.getOrganiser().getUsername());

        eventDTO.setMaxConfirmed(event.getMaxConfirmed());

        return eventDTO;
    }


}
