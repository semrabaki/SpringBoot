package rest_api04_controller_service_repository_basic_auth;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentBean05Config {
	
	@Bean //Used just in method level if the method is returning an object
	CommandLineRunner commanLineRunner1(StudentBean05Repository studentRepo) {
		
		return args -> {
							StudentBean05 std1 = new StudentBean05(101L, "Ali Can", "ac@gmail.com", LocalDate.of(2002, Month.JANUARY, 21));
							StudentBean05 std2 = new StudentBean05(102L, "Veli Han", "vh@gmail.com", LocalDate.of(2000, Month.AUGUST, 12));
							StudentBean05 std3 = new StudentBean05(103L, "Mary Star", "ms@gmail.com", LocalDate.of(2012, Month.JUNE, 5));
							
							studentRepo.saveAll(List.of(std1, std2, std3));
	
						};
		
	}

}
