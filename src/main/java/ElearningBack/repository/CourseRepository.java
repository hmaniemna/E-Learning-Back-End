package ElearningBack.repository;
import ElearningBack.model.Teacher;
import ElearningBack.model.Course;
import ElearningBack.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query(value ="Select * from courses C where C.teacher_course=:id", nativeQuery=true)
    List<Course> getcoursebyteacher(@Param("id") Long id);
    
    @Query(value ="Select * from courses C where C.group_course =(Select g.idg from groupes g, students s "
    		+ "where g.idg = s.idg and s.ids =:id) ", nativeQuery=true)
    List<Course> getcoursebystudent(@Param("id") Long id);
}
