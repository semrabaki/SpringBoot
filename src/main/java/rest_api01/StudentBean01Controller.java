package rest_api01;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller //it the layer of the class that handles all the request, understand the request type like if it is get or put etc
@RestController
public class StudentBean01Controller {
	
	//@RequestMapping(method=RequestMethod.GET, path="/getRequest")
	// it tells spring boot that this method will be used in request
	//@ResponseBody //To be able to see the message on the console
	@GetMapping(path="getRequest")
	public String getMethod1() {
		return "Get Request method is executed...";
		
		//Using @RestController and  @GetMapping (path="/getRequest") better because it is shorter
		//In this method we returned just String
	}
	
	@GetMapping(path="/getObject")
	public StudentBean01 getMethod2() {
		
		return new StudentBean01("Ali Can", 13, "AC202113");
		
	}
	
	//The previous method (getMethod2())is tight coupling, to make it loose coupling type the following method

	
	@Autowired //This annotation means  that you assigned the object(bean)to the s object
	StudentBean01 s;
	
	@GetMapping(path="/getXXX")
	public StudentBean01 getMethod3() {
		
		s.setAge(13);
		s.setName("Ali Can");
		s.setId("AC202113");
		return s;
		
	}
	
	//to return a parameterized object you should create this method;
	
	@GetMapping (path="/getParameterized/{school}")
	public StudentBean01 getMethod4(@PathVariable String school)
	{                                                           //%s shows the path of the variable
		return new StudentBean01 ("Ali Can", 13, String.format("AC%s202113",school));  //the output is {"name":"Ali Can","age":13,"id":"AC{school}202113"}
		//be careful about path="/getParameterized/{school}" and getMEthod4(@PathVariable String school)
		//this is tight coupling
	}
	
	
	//to make the method loose coupling:
	
	@Autowired
	StudentBean01 s2;
	
	@GetMapping(path="/getParameterized2/{school}")
	public StudentBean01 getMethod5(@PathVariable String school) {
		
		s2.setAge(13);
		s2.setName("Ali Can");
		s2.setId(String.format("AC%s202113", school));
		return s2;         //new StudentBean01 ("Ali Can", 13, String.format("AC%s202113",school));
		
	}
	
	//How to return Object list
	
	@GetMapping(path="/getList")
	public List <StudentBean01> getMethod6(){
		
		return List.of(
				
				new StudentBean01("AB", 12, "AB12"),
				new StudentBean01("CD",13, "CD13"),
				new StudentBean01("EF",14,"EF14")
				
				
				
				);
		
	}
	
	//For this method, we will learn loose coupling in Repository Layer
	
	//If you do not put any path inside the @GetMapping paranthesis it works as well
	
	@GetMapping
	public String getMEthod6() {
		return "Get Request for home page....";
	}
	
	//You cannot use same path inside the @GetMapping parantehsis it does not work
    // You cannot use same path inside the @GetMapping paranthesis it does not work
//  because there are 2 beans in IOC, Java cannot decide to select,
//  Because of that we use @Qualifier(value="studentBean01") if we want to select bean of "studentBean01"
//  If you use @Qualifier(value="studentBean02"), it means you selected bean of "studentBean02"
//  Default name of beans in IOC is same with the class name whose initial is lower case
	
	@Autowired
	@Qualifier(value="studentBean01")
	StudentInterface std;
	@GetMapping (path="/getMessage")
	public String getMethod7()
	{
		return std.study();
	}
	


	
	
	
//	@Autowired
//	StudentBean02 std;
//	@GetMapping (path="/getMessage")
//	public String getMethod8()
//	{
//		return std.study();
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
