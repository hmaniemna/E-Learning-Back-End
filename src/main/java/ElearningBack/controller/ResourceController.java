package ElearningBack.controller;


import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Event;
import ElearningBack.model.Resource;
import ElearningBack.repository.EventRepository;
import ElearningBack.repository.ResourceRepository;

//import org.daypilot.demo.angularscheduler.domain.Resource;
//import org.daypilot.demo.angularscheduler.controller.MainController.EventDeleteParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class ResourceController {
	
	  @Autowired
	    ResourceRepository rr;
		
	    @RequestMapping("/Resource")
	    @ResponseBody
	    String home() {
	        return "Welcome!";
	    }
	    
	    @RequestMapping("/Resources")
	    Iterable<Resource> resources() {
	        return rr.findAll();
	    }

}
