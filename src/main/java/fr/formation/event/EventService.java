package fr.formation.event;

import fr.formation.artist.ArtistRepository;
import fr.formation.user.UserDTO;
import fr.formation.user.UserRepository;
import fr.formation.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
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

    private static final Logger log = LoggerFactory.getLogger(EventService.class);

    public Event findById(Long id) {
        return eventRepository.findById(id).get();
    }

    public void addNewEvent(EventDTO eventDTO) throws UnsupportedEncodingException {
        //Recuperer l'user authentifié

        EventDTO eventDTOToAdd = eventDTO;
        String organiserUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        eventDTOToAdd.setOrganiserUsername(organiserUsername);
        UserDTO organiser = userService.findByUsername(organiserUsername);
        int organiserCodeCounty = Integer.parseInt(userService.findCodeCounty(organiser));
        List<UserDTO> usersToInvite = userService.findByCountyCode(organiserCodeCounty);

        if (usersToInvite != null) {
            eventDTOToAdd.setInvitatedIdList(
                    usersToInvite
                            .stream()
                            .map(userDTO -> userDTO.getId())
                            .collect(Collectors.toSet())
            );
        }

        usersToInvite.stream().forEach(user -> log.info("User id : " + user.getId()));
        //log.info("users to invite : " + usersToInvite);
        //ajouter tous les users du departements dans invitatedList

        eventRepository.save(createEvent(eventDTOToAdd));
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

        if (eventDTO.getConfirmedIdList() != null) {
            event.setConfirmedUserList(
                    eventDTO.getConfirmedIdList()
                            .stream()
                            .map(id -> userRepository.findById(id).get())
                            .collect(Collectors.toSet())
            );
        }

        if (eventDTO.getInvitatedIdList() != null) {
            event.setInvitatedUserList(
                    eventDTO.getInvitatedIdList()
                            .stream()
                            .map(id -> userRepository.findById(id).get())
                            .collect(Collectors.toSet())
            );
        }

        event.setOrganiser(userRepository.findByUsername(eventDTO.getOrganiserUsername()));

        event.setMaxConfirmed(eventDTO.getMaxConfirmed());

        return event;
    }

    public EventDTO createEventDTO(Event event) {

        EventDTO eventDTO = new EventDTO();

        eventDTO.setId(event.getId());
        eventDTO.setDate(event.getDate());
        eventDTO.setArtistName(event.getArtist().getArtistName());

        if (event.getConfirmedUserList() != null) {
            eventDTO.setConfirmedIdList(
                    event.getConfirmedUserList()
                            .stream()
                            .map(user -> user.getId())
                            .collect(Collectors.toSet())
            );
        }

        if (event.getInvitatedUserList() != null) {
            eventDTO.setInvitatedIdList(
                    event.getInvitatedUserList()
                            .stream()
                            .map(user -> user.getId())
                            .collect(Collectors.toSet())
            );
        }

        eventDTO.setOrganiserUsername(event.getOrganiser().getUsername());

        eventDTO.setMaxConfirmed(event.getMaxConfirmed());

        return eventDTO;
    }


}
