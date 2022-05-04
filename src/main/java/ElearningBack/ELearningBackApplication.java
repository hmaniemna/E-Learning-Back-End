package ElearningBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;



@EntityScan(
    basePackageClasses = { ELearningBackApplication.class, Jsr310JpaConverters.class }
)

@SpringBootApplication
public class ELearningBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningBackApplication.class, args);
		
	}

}
