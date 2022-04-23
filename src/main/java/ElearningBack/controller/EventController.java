package ElearningBack.controller;


import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Event;
import ElearningBack.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    //get all events
    @GetMapping("/events")
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    //create a new event rest api
    @PostMapping("/events")
    public Event createEvent(@Valid @RequestBody Event event){
        return eventRepository.save(event);
    }

    //get event by id rest api & return error if not found
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("event does not exist with this id: "+ id));
        return ResponseEntity.ok(event);
    }

    //update event rest api
    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent( @PathVariable Long id,@Valid @RequestBody Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("event not exists wih id :" + id));

       event.setDayEvent(eventDetails.getDayEvent());
       event.setMonthEvent(eventDetails.getMonthEvent());
       event.setYearEvent(event.getYearEvent());
       event.setLink(eventDetails.getLink());
       event.setDescription(eventDetails.getDescription());
       event.setNameEvent(eventDetails.getNameEvent());
       event.setDuration(eventDetails.getDuration());

        Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }

    //delete event rest api
    @DeleteMapping("/events/{id}")
    public ResponseEntity <Map<String,Boolean>>  deleteEvent(@PathVariable Long id){
        Event event= eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("event not exists with id:" + id));
        eventRepository.delete(event);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }


}
