package SpringBoot.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import({CommonConfig.class})

public class LearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);

	}

}
