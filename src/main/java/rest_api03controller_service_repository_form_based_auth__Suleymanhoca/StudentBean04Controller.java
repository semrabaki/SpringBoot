package rest_api03controller_service_repository_form_based_auth__Suleymanhoca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean04Controller {
	
	private StudentBean04Service studentService;

	@Autowired
	public StudentBean04Controller(StudentBean04Service studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping(path="/allStudents")
	public List<StudentBean04> getAllStudents(){
		return studentService.listStudents();
	}
	
	@GetMapping(path="/allStudents/{id}")
	public StudentBean04 getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}
	
	

}
