package ElearningBack.controller;


import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Student;
import ElearningBack.model.TimeTable;
import ElearningBack.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:60040")
@RequestMapping("/api/v1/")
public class TimeTableController {

    @Autowired
    private TimeTableRepository timeTableRepository;

    //get all timetables
    @GetMapping("/timetables")
    public List<TimeTable> getAllTimeTables(){
        return timeTableRepository.findAll();
    }


    //create a new timetable rest api
    @PostMapping("/timetables")
    public TimeTable createTimeTable(@Valid @RequestBody TimeTable timeTable){
        return timeTableRepository.save(timeTable);
    }

    //get timetable by id rest api & return error if not found
    @GetMapping("/timetables/{id}")
    public ResponseEntity<TimeTable> getTimeTableById(@PathVariable Long id){
        TimeTable timeTable = timeTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("timeTable does not exist with this id: "+ id));
        return ResponseEntity.ok(timeTable);
    }

    //get timeTable by idCourse rest api
    @GetMapping("/timetables/{idCourse}")
    public ResponseEntity<TimeTable> getTimeTableByIdCourse(@PathVariable Long idCourse){
        TimeTable timeTable = timeTableRepository.findById(idCourse)
                .orElseThrow(() -> new ResourceNotFoundException("timeTable does not exist with this idC: "+ idCourse));
        return ResponseEntity.ok(timeTable);
    }

    //update timeTable rest api
    @PutMapping("/timetables/{id}")
    public ResponseEntity<TimeTable> updateTimeTable( @PathVariable Long id,@Valid @RequestBody TimeTable timeTableDetails) {
        TimeTable timeTable = timeTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("timeTable not exists wih id :" + id));

        timeTable.setCourse(timeTableDetails.getCourse());
        timeTable.setDay(timeTableDetails.getDay());
        timeTable.setLessonNumber(timeTableDetails.getLessonNumber());
        timeTable.setGroup(timeTableDetails.getGroup());

        TimeTable updatedTimeTable = timeTableRepository.save(timeTable);
        return ResponseEntity.ok(updatedTimeTable);

    }

    //delete timeTable rest api
    @DeleteMapping("/timetables/{id}")
    public ResponseEntity <Map<String,Boolean>>  deleteTimeTable(@PathVariable Long id){
        TimeTable timeTable = timeTableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("timeTable not exists with id:" + id));
        timeTableRepository.delete(timeTable);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }
}
