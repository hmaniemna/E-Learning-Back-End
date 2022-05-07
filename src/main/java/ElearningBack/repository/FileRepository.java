package ElearningBack.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ElearningBack.model.File;
@Repository
public interface FileRepository extends JpaRepository<File, Long> {


}

