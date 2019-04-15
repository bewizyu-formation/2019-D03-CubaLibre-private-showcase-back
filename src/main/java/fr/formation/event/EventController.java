package fr.formation.event;

import fr.formation.artist.ArtistService;
import fr.formation.controllers.AbstractController;
import fr.formation.user.UserDTO;
import fr.formation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Event controller.
 */

@RestController
@RequestMapping("/event")
public class EventController extends AbstractController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArtistService artistService;

    @PostMapping("/new")
    public void addEvent(@RequestBody EventDTO eventDTO) {
        eventService.addNewEvent(eventDTO);
    }

    @GetMapping("/")
    public List<EventDTO> findAllEventsByAuthenticatedArtist(){
        UserDTO userDTO = getAuthenticatedUserDTO();
        return eventService.findByArtistName(userDTO.getArtistName());
    }

    @GetMapping("/{artistName}")
    public List<EventDTO> findAllEventsByArtist(@PathVariable String artistName){
        return eventService.findByArtistName(artistName);
    }

    @GetMapping("/all")
    public List<EventDTO> findAllEvents(){
        return eventService.findAll();
    }

    @PutMapping("/")
    public void updateEvent(@RequestBody EventDTO eventDTO){
        eventService.saveEvent(eventDTO);
    }

}
