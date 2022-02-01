package rest_api02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController //It contains responsebody and Controller annotations
public class StudentBean03Controller {
	
	private StudentBean03Service studentBean03Service;
	
	@Autowired
   StudentBean03Service student;
	
	@GetMapping(path="/allStudents")
	public List<StudentBean03>getAllStudents(){
		return student.getListOfStudents();
	}
	
	//This method is to return a specific object (Controller level)
	
	@GetMapping(path="/studentById/{id}")
	public StudentBean03 getStudentById(@PathVariable Long id){
		
		return student.getStudentById(id);
	}
	
	

}
