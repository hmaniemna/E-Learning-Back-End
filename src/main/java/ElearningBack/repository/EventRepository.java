package ElearningBack.repository;

import ElearningBack.model.Event;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public interface EventRepository extends CrudRepository<Event,Long> {
	
	@Query("from Event e where not(e.end < :from and e.start > :to)")
	public List<Event> findBetween(@Param("from") LocalDateTime start, @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDateTime end);

}
