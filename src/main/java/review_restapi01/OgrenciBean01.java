package review_restapi01;

import org.springframework.stereotype.Component;

@Component
public class OgrenciBean01 implements OgrenciInterface{
	
	private String name;
	private int age;
	private String id;
	OgrenciBean01() {
	 
		System.out.println("OgrenciBean01 Constructor without parameter is used");
	}
	OgrenciBean01(String name, int age, String id) {
		
		this.name = name;
		this.age = age;
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	@Override
	public String study() {
		
		return "Students must study 01";
	}
	@Override
	public String toString() {
		return "OgrenciBean01 [name=" + name + ", age=" + age + ", id=" + id + "]";
	}
	
	
	

}
