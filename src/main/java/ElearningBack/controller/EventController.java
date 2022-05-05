package ElearningBack.controller;

import ElearningBack.model.Event;
import ElearningBack.model.TimeInterval;
import ElearningBack.repository.EventRepository;
import ElearningBack.repository.TimeIntervalRepository;
//import org.daypilot.demo.angularscheduler.controller.MainController.EventDeleteParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import javax.transaction.Transactional;
import java.time.LocalDateTime;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EventController {

    @Autowired
    EventRepository er;
    
    @Autowired
    TimeIntervalRepository ti;
	
	
    
	
    @RequestMapping("/event")
    @ResponseBody
    String home() {
        return "Welcome!";
    }
    
    @GetMapping("/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime from, @RequestParam("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime to) {
    	System.out.println(from);
    	System.out.println(to);
    	return er.findBetween(from, to);    	
    }
    
    @PostMapping("/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody EventCreateParams params) {
    	
    	System.out.println(params.TimeIId);
    	System.out.println(params.text+params.start.toString()+params.end.toString());
    	
        TimeInterval t = ti.findOne(params.TimeIId); 
        System.out.println(t);
    	
        Event e = new Event();
    	e.setStart(params.start);
    	e.setEnd(params.end);
    	e.setText(params.text);
    	e.setTimeInterval(t);
    	
    	er.save(e);
    	
    	return e;
    }


    public static class EventCreateParams {
    	public LocalDateTime start; 
  		public LocalDateTime end;
	  	public String text;
		public Long TimeIId;
    }
    
    
    

}
