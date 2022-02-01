package review_restapi01;

import org.springframework.stereotype.Component;

@Component
public class OgrenciBean02 implements OgrenciInterface{
	
	private String name;
	private int age;
	
	OgrenciBean02() {
		System.out.println("OgrenciBean02 constructor without parameter is used");
		
	}
	OgrenciBean02(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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
	
	@Override
	public String study() {
		
		return "Students must study 02";
	}
	@Override
	public String toString() {
		return "OgrenciBean02 [name=" + name + ", age=" + age + "]";
	}
	
	
	

}
