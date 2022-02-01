package restApi03;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentBean04Config {
	@Bean //if method is returning object you should bean annotation
	CommandLineRunner commandLineRunner (StudentBean04Repository studentRepo) {
		
		return args->{
			StudentBean04 std1 =    new StudentBean04(101L, "Ali Can", "ac@gmail.com", LocalDate.of(2008, 8, 8));
            StudentBean04 std2 =    new StudentBean04(102L, "Veli Han", "vh@gmail.com", LocalDate.of(1996, 6, 6));
            StudentBean04 std3 =    new StudentBean04(103L, "Ayse Kan", "ak@gmail.com", LocalDate.of(2000, 1, 1));
            StudentBean04 std4 =    new StudentBean04(104L, "Mary Star", "ms@gmail.com", LocalDate.of(1995, 5, 5));
            
            studentRepo.saveAll(List.of(std1,std2,std3,std4));
		
	      };
	
	}

}
