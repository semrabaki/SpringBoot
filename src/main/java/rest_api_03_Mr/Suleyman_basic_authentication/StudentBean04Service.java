package rest_api_03_Mr.Suleyman_basic_authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StudentBean04Service {
	
private StudentBean04Repository studentRepo;
	
	@Autowired
	public StudentBean04Service(StudentBean04Repository studentRepo) {
		this.studentRepo = studentRepo;
	}
	
	
	//For Get REquest Method
	public List<StudentBean04> listStudents(){
		return studentRepo.findAll();
	}
	
	//For Get Request Method
	public StudentBean04 getStudentById(Long id) {
		
		if(studentRepo.findById(id).isPresent()) {
			return studentRepo.findById(id).get();
		}else {
			return new StudentBean04();
		}
		
	}
	
	
	//For DELETE REquest EMthod
	
 public String deleteStudentById(Long id)
	{
	 
	 if(!studentRepo.existsById(id)) { //instead of throwing exception you can just return a sting message but I want to stop the code excetioon that is why I am throwing exception
		 throw new IllegalStateException(id + "does not esixt");
	 }
	 else {
		 studentRepo.deleteById(id);
		 return "Student whose id is " +id + " is successfully deleted";
	 }
		
	}
 

	/*
	 	For PUT Request Method
		 	Logic to update name
		 	1)If user does not send name, name must be null
		 	2)If user sends the same name app should not do any update
		 	3)If user sends different name app should update
		 	4)I will select the student to update by id, if Id does not exist I should throw Exception
		 	
		 	Logic to update email
		 	1)If user sends existing email, throw exception because emails must be unique
		 	2)If user does not send email throw Exception
		 	3)If user sends email in invalid format (Must have @ sign) throw Exception 
		 	4)If user sends same email no need to update
		 	5)If user sends different email do update
		 	
		 	Logic to update DOB
		 	1)If the date is after current date throw Exception
		 	2)If the date is different do update
		 	3)If the date is same no need do update
		 	
		 	
	*/
	public StudentBean04 fullyUpdateStudent(Long id, StudentBean04 newStudent) {
		
		StudentBean04 existingStudentById = studentRepo.findById(id).orElseThrow(()-> new IllegalStateException(id + " does not exist"));
		
		//To update name
		if(newStudent.getName()==null) {
			existingStudentById.setName(null);
		}else if(!existingStudentById.getName().equals(newStudent.getName())) {
			existingStudentById.setName(newStudent.getName());
		}
		
		//To update email
		Optional<StudentBean04> esixtingStudentByEmail = studentRepo.findStudentBean04ByEmail(newStudent.getEmail());

		if(esixtingStudentByEmail.isPresent()) {
			throw new IllegalStateException(newStudent.getEmail() + " exists. Emails must be unique...");
		}else if(newStudent.getEmail()==null) {
			throw new IllegalArgumentException("Email is mandatory to update data...");
		}else if(!newStudent.getEmail().contains("@")) {
			throw new IllegalArgumentException("Email is invalid please use valid format...");
		}else if(!newStudent.getEmail().equals(existingStudentById.getEmail())) {
			existingStudentById.setEmail(newStudent.getEmail());
		}
		
		//To update DOB 
		if(Period.between(newStudent.getDob(), LocalDate.now()).isNegative()) {
			throw new IllegalStateException("Date of birth cannot be greater than current date");
		}else if(!existingStudentById.getDob().equals(newStudent.getDob())) {
			existingStudentById.setDob(newStudent.getDob());
		}
		
		//To update Age
		existingStudentById.setAge(newStudent.getAge());
		
		//To update error message
		existingStudentById.setErrMsg("No error...");
		
		return studentRepo.save(existingStudentById);
	}
	
	/*
	 	For PATCH Request (Partial Update)
	 	To update name
	 	1)If the new name is not null, it will be updated
	 	2)If the new name is different from existing name it will be updated
	 	
	 	To update email
	 	1)If the new email is null throw Exception
	 	2)If the new email is different from existing email it will be updated
	 	3)If the email exists throw Exception
	 	
	 	To update dob
	 	1)If the date is future throw Exception
	 	2)If the date is different from existing one do update

	 */
	
	public StudentBean04 partiallyUpdateStudent(Long id, StudentBean04 newStudent)
	{
		StudentBean04 existingStudentById= studentRepo.findById(id).orElseThrow(()-> new IllegalStateException(id+ "   does not exist in the repo"));
		
		if(newStudent.getName()!=null && newStudent.getName().equals(existingStudentById.getName())) {
			existingStudentById.setName(newStudent.getName());
		}
		
		//To update email
		
		Optional<StudentBean04>existingStudentByEmail= studentRepo.findStudentBean04ByEmail(newStudent.getEmail());
		
		if(existingStudentByEmail.isPresent()) {
			throw new IllegalStateException("Email must be unique...");
	    }else if (newStudent.getEmail()==null) {
	    	throw new IllegalStateException("Email connaot be null, it is mandatory... "); //this should be before the @ because null email do not contain it
	    	
	    }else if(newStudent.getEmail().contains("@")) {
	    	
	    	throw new IllegalStateException("Email must contain @ sign...");
	    	
	    }else if(newStudent.getEmail()!=null) {
	    	
	    	existingStudentById.setEmail(newStudent.getEmail());
	    }
	
	
	   //To update dob

		if(Period.between(newStudent.getDob(), LocalDate.now()).isNegative()) {
			throw new IllegalStateException("Date of birth cannot be greater than current date");
		}else if(newStudent.getDob()!=null)
		{
			existingStudentById.setDob(newStudent.getDob());
		}
		
		// To update age
		
		existingStudentById.setAge(newStudent.getAge());
		
		//To update error message
		
		existingStudentById.setErrMsg("No Error....");
		
		
	
		return studentRepo.save(existingStudentById);
}
	

	
    /*
     For Post Request
     1) If the email exists throw Exception
     2)If the email is null new data cannot be created
     
     	Note To create new data in DB , you must create connection with DB     */
	
	public StudentBean04 addNewStudent(StudentBean04 newStudent) throws ClassNotFoundException, SQLException {
		
		Optional<StudentBean04>existingStudentByEmail= studentRepo.findStudentBean04ByEmail(newStudent.getEmail());
		
		if(existingStudentByEmail.isPresent()) {
			throw new IllegalStateException("Email exists, make it unique...");
		}
		
		if(newStudent.getEmail()==null) {
			throw new IllegalStateException("Without using email, new data cannot be created");
		}
		
		//To create connection with DB
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");
				
				Statement st = con.createStatement();
				
				String sqlQuery = "SELECT MAX(id) FROM students"; //I need to get the max id because when you add a new id  I am getting last id and increase it by one
				
				ResultSet result = st.executeQuery(sqlQuery);
				
				Long maxId=0L;
		
				while (result.next()) {
					maxId=result.getLong(1); //I put 1 in get because it is referencing the id column in the database which is the first column in the database you can also write it " id" likehat
				}
				
				newStudent.setId(maxId +1);
				newStudent.setAge(newStudent.getAge());
				newStudent.setErrMsg("No Error");
				
				return studentRepo.save(newStudent);
		
		
	}
	
	

}
