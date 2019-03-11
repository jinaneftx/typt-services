package com.e.typt.core.entity.dto.ws;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "result", namespace = "")
//@XmlSeeAlso({ObjDTO.class})
public class ObjDTO {
	private String name;
	private int age;
	
	public ObjDTO() {
		// TODO Auto-generated constructor stub
	}

	public ObjDTO(String name, int age) {
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
	public String toString() {
		// TODO Auto-generated method stub
		return "name:"+name+",age:"+age;
	}
}
