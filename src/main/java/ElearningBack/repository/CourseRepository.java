package ElearningBack.repository;

import ElearningBack.model.Course;
import ElearningBack.model.Student;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course,Long> {
	
	  @Query(value ="Select id_course,title,start_date,end_date,teacher_id from courses", nativeQuery=true)
	    List<Course> findAll();
}
