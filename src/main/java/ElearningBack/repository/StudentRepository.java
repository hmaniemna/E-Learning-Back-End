package ElearningBack.repository;

import ElearningBack.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query(value ="Select * from students S where S.email=:email and S.password=:password", nativeQuery=true)
    Student getStudentFromEmailAndPassword(@Param("email") String email,@Param("password") String password);
    
    @Query(value ="Select S.ids from students S where S.ids=:ids", nativeQuery=true)
    Long getStudentId(@Param("ids")Long ids);
	
    
	
}



