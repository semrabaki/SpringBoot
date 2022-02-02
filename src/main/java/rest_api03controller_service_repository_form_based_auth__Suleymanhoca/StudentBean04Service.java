package rest_api03controller_service_repository_form_based_auth__Suleymanhoca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentBean04Service {
	
	private StudentBean04Repository studentRepo;
	
	@Autowired
	public StudentBean04Service(StudentBean04Repository studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	public List<StudentBean04> listStudents(){
		return studentRepo.findAll();
	}
	
	public StudentBean04 getStudentById(Long id) {
		
		if(studentRepo.findById(id).isPresent()) {
			return studentRepo.findById(id).get();
		}else {
			return new StudentBean04();
		}
		
	}

}
