package review_restapi01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import rest_api01.StudentBean01;

@RestController
public class OgrenciBean01Controller {
	
	@GetMapping(path="/getRequest")
	public String getMethod1() {
		
		return "Get Request method is executed...";
	}

	@GetMapping(path="/getObject")
	public OgrenciBean01 getMethod2() {
		
		return new OgrenciBean01 ("Ali Can", 13, "AC202113");
		
	}
	
	//the previous is tight coupling 
	
	@Autowired
	OgrenciBean01 o;
	
	@GetMapping(path="/getXXX")
	public OgrenciBean01 getMethod3() {
		
		o.setAge(13);
		o.setId("AC202113");
		o.setName("Ali Can");
		
		return o;
	}
	
	//to return a parameterized object you should create this method;
	
	@GetMapping(path= "/getParameterized/{school}")
	public OgrenciBean01 getMethod4(@PathVariable String school) {
		
		return new OgrenciBean01("Ali Can", 13, String.format("AC%s202113",school)); //what ever you put inthe path it will be in the id whre %s located
		
	}
	
	@Autowired
	OgrenciBean01 s2;
	
	@GetMapping(path="/getParameterized2/{school}")
	public OgrenciBean01 getMethod5(@PathVariable String school) {
		s2.setAge(13);
		s2.setName("Semra Baki");
		s2.setId(String.format("AC%s202113", school));
		
		return s2;
		
	}
	
	//How to return Object list
	
		@GetMapping(path="/getList")
		public List <OgrenciBean01> getMethod6(){
			
			return List.of(
					
					new OgrenciBean01("AB", 12, "AB12"),
					new OgrenciBean01("CD",13, "CD13"),
					new OgrenciBean01("EF",14,"EF14")
					
					
					
					);
			
		}
		
		//For this method, we will learn loose coupling in Repository Layer
		
		//If you do not put any path inside the @GetMapping paranthesis it works as well
		
		@GetMapping
		public String getMEthod6() {
			return "Get Request for home page....";
		}
		
		
		
//		@Autowired
//		OgrenciBean02 std;
//		
//		@GetMapping(path="/getMessage")
//		public String Method07() {
//			
//			return std.study();
//			
//		}
		
		@Autowired
		@Qualifier(value="ogrenciBean02")
		OgrenciInterface std;
		
		@GetMapping(path="/getMessage")
		public String Method07() {
			
			return std.study();
			
		}
	
}
