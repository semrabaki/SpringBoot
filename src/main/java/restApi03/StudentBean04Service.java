package restApi03;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentBean04Service {
	
	@Autowired
	StudentBean04Repository studentRepo;
	
	public StudentBean04 findStudentById(Long id) {
		
		if(studentRepo.findById(id).isPresent()) {
		
		return studentRepo.findById(id).get();
	}
		return new StudentBean04();
	

}
	
	public List<StudentBean04> findAllStudents(){
		return studentRepo.findAll();
		
	}
}