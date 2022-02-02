package rest_api02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//import rest_api02_controller_service.StudentBean03Service;

@RestController //It contains responsebody and Controller annotations
public class StudentBean03Controller {
	
	private StudentBean03Service studentBean03Service;
	
	@Autowired //will get the object from IOC and put it in the consturctor paramater container
	public StudentBean03Controller(StudentBean03Service studentBean03Service) {
		this.studentBean03Service = studentBean03Service;
	}
   
   
	
	@GetMapping(path="/allStudents")
	public List<StudentBean03>getAllStudents(){
		return studentBean03Service.getListOfStudents();
	}
	
	//This method is to return a specific object (Controller level)
	
	@GetMapping(path="/studentById/{id}")
	public StudentBean03 getStudentById(@PathVariable Long id){
		
		return studentBean03Service.getStudentById(id);
	}
	
	

}
