package com.project.ces.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;


@Entity
public class StudentEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long rollno;

	@NotBlank(message = "Name is required")
	@Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
	@Pattern(regexp = "^[a-zA-Z ]*$", message = "Name can only contain alphabetic characters and spaces")
	private String name;

	private String address;

	@Email(message = "Invalid email address")
	private String email;

	@NotNull(message = "Phone number is required")
	private Long phoneno;

	@NotNull(message = "Age is required")
	@Min(value = 3, message = "Age must be greater than or equal to 3")
	@Max(value = 100, message = "Age must be less than or equal to 100")
	private int age;
	
	public StudentEntity (String name, long rollno, String address, String email, long phoneno, int age) {
		super();
		this.name = name;
		this.rollno = rollno;
		this.address = address;
		this.email = email;
		this.phoneno = phoneno;
		this.age = age;
	}
	
	public StudentEntity () {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getRollno() {
		return rollno;
	}

	public void setRollno(long rollno) {
		this.rollno = rollno;
	}
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(long phoneno) {
		this.phoneno = phoneno;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
