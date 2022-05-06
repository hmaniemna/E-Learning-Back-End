package ElearningBack.repository;

import ElearningBack.model.Course;
import ElearningBack.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query(value ="Select * from courses T where T.teacher_course=:id", nativeQuery=true)
    List<Course> getcoursebyteacher(@Param("id") Long id);
}
