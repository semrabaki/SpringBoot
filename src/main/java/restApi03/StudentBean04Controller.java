package restApi03;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentBean04Controller {
	
	
    @Autowired
	StudentBean04Service studentBean04Service;
    
    @GetMapping(path="/findStudentById/{id}")
    public StudentBean04 findStudentById(@PathVariable Long id) {
    	return studentBean04Service.findStudentById(id);
    }
    
    @GetMapping(path="/findAll")
    public List<StudentBean04>findAllStudents(){
    	return studentBean04Service.findAllStudents();
    }
}
