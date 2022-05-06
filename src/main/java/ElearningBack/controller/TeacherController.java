package ElearningBack.controller;


import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Student;
//import ElearningBack.model.Student;
import ElearningBack.model.Teacher;
import ElearningBack.repository.TeacherRepository;
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
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    //Get all teachers
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    //create a new teacher rest api
    @PostMapping("/teachers")
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    //login for a teacher
    @GetMapping("/teachers/{email}/{password}")
    public ResponseEntity<?> getStudentFromEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        System.out.println(email + password);


        Teacher teacher = teacherRepository.getTeacherFromEmailAndPassword(email, password);
        //.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + idS));
        System.out.println(teacher);
        //if (student.getPassword().equals(password))
        return ResponseEntity.ok(teacher);
        //else
        //return (ResponseEntity<?>) ResponseEntity.internalServerError();

    }


    //get teacher by id rest api & return error if not found
    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("teacher does not exist with this id: " + id));
        return ResponseEntity.ok(teacher);
    }

    //update teacher rest api
    @PutMapping("/teachers/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @Valid @RequestBody Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("teacher not exists wih id :" + id));

        teacher.setAccessCode(teacherDetails.getAccessCode());
        teacher.setEmailId(teacherDetails.getEmailId());

        teacher.setFullName(teacherDetails.getFullName());
        teacher.setAccessCode(teacherDetails.getAccessCode());
        teacher.setPassword(teacherDetails.getPassword());

        teacher.setFullName(teacherDetails.getFullName());
        ;
        teacher.setAccessCode(teacherDetails.getAccessCode());
        teacher.setPassword(teacherDetails.getPassword());
        teacher.setGroupT(teacherDetails.getGroupT());
        //teacher.setStudentss(teacherDetails.getStudentss());


        Teacher updatedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.ok(updatedTeacher);

    }
}