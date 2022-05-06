package ElearningBack.repository;
import ElearningBack.dto.ResponseRequest;
import ElearningBack.model.Course;
import ElearningBack.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query(value ="Select * from teachers T where T.email_id=:email and T.pass_word=:password", nativeQuery=true)
    Teacher getTeacherFromEmailAndPassword(@Param("email") String email,@Param("password") String password);



  //list of teacher , course
    @Query(value ="select new ResponseRequest(t.fullName , c.title )" +
            " from Teacher t JOIN t.coursesT c"
             , nativeQuery=true )
    List<ResponseRequest> getjoininformations();


}

