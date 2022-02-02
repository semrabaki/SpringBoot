package rest_api_03_Mr.Suleyman_basic_authentication;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class StudentBean04Config {
	
	
	
	@Bean//Used just in method level if the method is returning an object
	CommandLineRunner commandLineRunner1 (StudentBean04Repository studentRepo) {
		
		
		return args-> {
			
			StudentBean04 std1 = new StudentBean04(101L, "Ali Can", "ac@gmail.com", LocalDate.of(2002, Month.JANUARY, 21));
			StudentBean04 std2 = new StudentBean04(102L, "Veli Han", "vh@gmail.com", LocalDate.of(2000, Month.AUGUST, 12));
			StudentBean04 std3 = new StudentBean04(103L, "Mary Star", "ms@gmail.com", LocalDate.of(2012, Month.JUNE, 5));
			
			studentRepo.saveAll(List.of(std1, std2, std3));
			                  
		};
		
	}

}
