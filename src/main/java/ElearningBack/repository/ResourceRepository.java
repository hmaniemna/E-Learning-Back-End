package ElearningBack.repository;


import org.springframework.data.jpa.repository.Query;
//import org.daypilot.demo.angularscheduler.domain.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ElearningBack.model.Resource;
import ElearningBack.model.Student;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
	
	@Query(value ="Select * from resource R where R.id=:resourceId", nativeQuery=true)
    Resource findOne(@Param("resourceId") Long resourceId);
			

}




