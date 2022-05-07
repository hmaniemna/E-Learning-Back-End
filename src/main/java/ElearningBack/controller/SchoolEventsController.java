package ElearningBack.controller;


import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.SchoolEvents;
import ElearningBack.repository.SchoolEventsRepository;
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

public class SchoolEventsController {

    @Autowired
    private SchoolEventsRepository eventRepository;

    //get all events
    @GetMapping("/schoolevents")
    public List<SchoolEvents> getAllEvents(){
        return eventRepository.findAll();
    }

    //create a new event rest api
    @PostMapping("/schoolevents")
    public SchoolEvents createEvent(@Valid @RequestBody SchoolEvents event){
        return eventRepository.save(event);
    }

    //get event by id rest api & return error if not found
    @GetMapping("/schoolevents/{id}")
    public ResponseEntity<SchoolEvents> getEventById(@PathVariable Long id){
        SchoolEvents event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("event does not exist with this id: "+ id));
        return ResponseEntity.ok(event);
    }

    //update event rest api
    @PutMapping("/schoolevents/{id}")
    public ResponseEntity<SchoolEvents> updateEvent( @PathVariable Long id,@Valid @RequestBody SchoolEvents eventDetails) {
        SchoolEvents event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("event not exists wih id :" + id));

        event.setDayEvent(eventDetails.getDayEvent());
        event.setMonthEvent(eventDetails.getMonthEvent());
        event.setYearEvent(event.getYearEvent());
        event.setLink(eventDetails.getLink());
        event.setDescription(eventDetails.getDescription());
        event.setNameEvent(eventDetails.getNameEvent());
        event.setDuration(eventDetails.getDuration());

        SchoolEvents updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }

    //delete event rest api
    @DeleteMapping("/schoolevents/{id}")
    public ResponseEntity <Map<String,Boolean>>  deleteEvent(@PathVariable Long id){
        SchoolEvents event= eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("event not exists with id:" + id));
        eventRepository.delete(event);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }

}