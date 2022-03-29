package ElearningBack.controller;

import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Student;
import ElearningBack.repository.StudentRepository;
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
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    //get all students
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    //logging for student (problem here)
    // @PostMapping("/students")
    //  public ResponseEntity<?> connectStudent(@RequestBody Student studentDetails){
    //    System.out.println(studentDetails);

    //   Student student = studentRepository.findById(studentDetails.getIdS())
    //          .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + studentDetails.getIdS()));
    //   if (student.getPassword().equals(studentDetails.getPassword()))
    //      return ResponseEntity.ok(student);
    //    else
    //       return (ResponseEntity<?>) ResponseEntity.internalServerError();

    // }

    //create a new student rest api
    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student){
        return studentRepository.save(student);
    }

    //get student by id rest api & return error if not found
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student does not exist with this id: "+ id));
        return ResponseEntity.ok(student);
    }

    //update student rest api
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent( @PathVariable Long id,@Valid @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not exists wih id :" + id));

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmailId(studentDetails.getEmailId());
        student.setGroup(studentDetails.getGroup());
        student.setLevel(studentDetails.getLevel());
        student.setPassword(studentDetails.getPassword());
        student.setAccessCode(studentDetails.getAccessCode());
      //  student.setTeachers(studentDetails.getTeachers());

        Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);

    }

    //delete student rest api
    @DeleteMapping("/students/{id}")
    public ResponseEntity <Map<String,Boolean>>  deleteStudent(@PathVariable Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not exists with id:" + id));
        studentRepository.delete(student);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }
}

