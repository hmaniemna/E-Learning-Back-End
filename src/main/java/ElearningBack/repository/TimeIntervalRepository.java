package ElearningBack.repository;


import org.springframework.data.jpa.repository.Query;
//import org.daypilot.demo.angularscheduler.domain.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ElearningBack.model.TimeInterval;

public interface TimeIntervalRepository extends CrudRepository<TimeInterval, Long> {
	
	@Query(value ="Select * from time_interval Ti where Ti.id=:TimeIntervalId", nativeQuery=true)
    TimeInterval findOne(@Param("TimeIntervalId") Long TimeIId);

}
