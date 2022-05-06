package ElearningBack.controller;


import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Course;
import ElearningBack.repository.CourseRepository;
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
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    //get all courses
    @GetMapping("/courses")
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    //create a new course rest api
    @PostMapping("/courses")
    public Course createCourse(@Valid @RequestBody Course course){
        return courseRepository.save(course);
    }

    //get course by id rest api & return error if not found
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("course does not exist with this id: " + id));
        return ResponseEntity.ok(course);
    }

    @GetMapping("/courselist/{teacherid}")
    public ResponseEntity<?> getCourseByteacherId(@PathVariable Long teacherid) {
        System.out.println(teacherid);


        List<Course> course = courseRepository.getcoursebyteacher(teacherid);
        //.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + idS));
        System.out.println(course);
        //if (student.getPassword().equals(password))
        return ResponseEntity.ok(course);
        //else
        //return (ResponseEntity<?>) ResponseEntity.internalServerError();**/

    }

    //update course rest api
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse( @PathVariable Long id,@Valid @RequestBody Course courseDetails) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("course not exists wih id :" + id));

        course.setTitle(courseDetails.getTitle());
        course.setYear(courseDetails.getYear());
       // course.setTeacher(courseDetails.getTeacher());
        course.setDescription(courseDetails.getDescription());

        Course updatedCourse = courseRepository.save(course);
        return ResponseEntity.ok(updatedCourse);
    }

    //delete course rest api
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteCourse(@PathVariable Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("course not exists with the id:"+ id));
        courseRepository.delete(course);

        Map<String,Boolean> response= new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}