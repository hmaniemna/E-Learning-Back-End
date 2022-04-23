package ElearningBack.repository;

import ElearningBack.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query(value ="Select * from admin A where A.code=:code and A.password=:password", nativeQuery=true)
    Admin getAdminFromCodeAndPassword(@Param("code") Integer code, @Param("password") String password);

}
