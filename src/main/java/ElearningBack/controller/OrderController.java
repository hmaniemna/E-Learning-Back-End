package ElearningBack.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ElearningBack.repository.TeacherRepository ;
import ElearningBack.repository.CourseRepository;
import ElearningBack.model.Teacher ;
import ElearningBack.dto.Orderrequest ;
import java.util.List;
import ElearningBack.dto.ResponseRequest;

@RestController
public class OrderController {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/createTeacher")
    public Teacher createTeacher(@RequestBody Orderrequest request){
        return teacherRepository.save(request.getTeacher());
    }


    @GetMapping("/getInfo")
    public List<ResponseRequest> getJoinInformation(){
        return teacherRepository.getjoininformations();
    }
}
