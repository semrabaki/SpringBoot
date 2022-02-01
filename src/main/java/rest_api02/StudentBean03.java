package rest_api02;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

//@Entity
//@Table(name="students")
@Component
public class StudentBean03 {
	
	@Id
	private Long id;
	private String name;
	private String email;
	private LocalDate dob;
	
	@Transient //This annotation makes age invisiable on the table in the db
	private Integer age;
	
	StudentBean03() {
		this.errMsg="There is no student like that....";
		
	}
	private String errMsg;
	StudentBean03(Long id, String name, String email, LocalDate dob) {
	
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.errMsg="No error....";
	
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public Integer getAge() {
		if(this.dob==null)
		{
			return null;
		}
		return Period.between(this.dob,LocalDate.now()).getYears();
	
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	
	public void  setErrMg(String errMsg) {
		
		this.errMsg=errMsg;
	}
	@Override
	public String toString() {
		return "StudentBean03 [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age
				+ "]";
	}
	
	
	

}
