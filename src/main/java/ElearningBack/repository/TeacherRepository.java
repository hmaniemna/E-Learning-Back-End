package ElearningBack.repository;

import ElearningBack.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
	
	@Query(value ="Select * from teachers T where T.email_id=:email and T.pass_word=:password", nativeQuery=true)
    Teacher getTeacherFromEmailAndPassword(@Param("email") String email,@Param("password") String password);
}
