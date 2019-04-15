package fr.formation.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event findById(Long id){
        return eventRepository.findById(id).get();
    }


}
