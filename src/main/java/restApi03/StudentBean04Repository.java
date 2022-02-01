package restApi03;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;

@Repository
public interface StudentBean04Repository extends JpaRepository <StudentBean04, Long> {
	
	Optional<StudentBean04> findStudentBean04ByEmail(String email);  // in spring boot after if you are writing non existing method non existing method with name
	//...By..., it automatically check database for it. it just pulls the data you need. in this case it is email
	
	

	

}
