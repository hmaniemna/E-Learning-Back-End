package ElearningBack.controller;

import ElearningBack.model.TimeInterval;
import ElearningBack.repository.TimeIntervalRepository;
//import org.daypilot.demo.angularscheduler.domain.Resource;
//import org.daypilot.demo.angularscheduler.controller.MainController.EventDeleteParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")

public class TimeIntervalController {

	
	@Autowired
    TimeIntervalRepository ti;
	
    @RequestMapping("/TimeInterval")
    @ResponseBody
    String home() {
        return "Welcome!";
    }
    
    @RequestMapping("/TimeIntervals")
    Iterable<TimeInterval> TimeIntervals() {
        return ti.findAll();
    }

}
