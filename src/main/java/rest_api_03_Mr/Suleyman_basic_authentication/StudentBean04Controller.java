package rest_api_03_Mr.Suleyman_basic_authentication;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentBean04Controller {
	
	private StudentBean04Service studentService;

	@Autowired
	public StudentBean04Controller(StudentBean04Service studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping(path="/allStudents")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")  //if I want my get method to open all users I use preauthorize and put the roles in it
	public List<StudentBean04> getAllStudents(){
		return studentService.listStudents();
	}
	
	@GetMapping(path="/allStudents/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')") 
	public StudentBean04 getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}
	
	@DeleteMapping(path="/deleteStudent/{id}")
	@PreAuthorize("hasAuthority('student:write')") //@PreAuthorize("hasAnyRole('ROLE_STUDENT')") this both works instead of just saying role by role you just indicate that any body who has write role
	public String deleteStudentById(@PathVariable Long id) {
		
		return studentService.deleteStudentById(id);
		
	}
	
	
	@PutMapping(path="/updateStudentFully/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public StudentBean04 fullyUpdateStudentById(@PathVariable Long id,@Validated @RequestBody StudentBean04 newStudent) { //since i am using object in here i am using @requestnody
		return studentService.fullyUpdateStudent(id, newStudent);
	}
	
	@PatchMapping(path="/updateStudentPartially/{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public StudentBean04 partiallyUpdateStudentById(@PathVariable Long id, @RequestBody StudentBean04 newStudent) {
		return studentService.partiallyUpdateStudent(id, newStudent);
	}	

	@PostMapping(path="/addNewStudent")
	@PreAuthorize("hasAuthority('student:write')")
	public StudentBean04 postStudent( @Validated @RequestBody StudentBean04 newStudent) throws ClassNotFoundException, SQLException {
		return studentService.addNewStudent(newStudent);
	}	

	
	
	
}
