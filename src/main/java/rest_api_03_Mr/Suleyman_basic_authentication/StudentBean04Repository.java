package rest_api_03_Mr.Suleyman_basic_authentication;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentBean04Repository extends JpaRepository <StudentBean04, Long>{
	
	
	
	Optional<StudentBean04> findStudentBean04ByEmail(String email);
	
	

}
