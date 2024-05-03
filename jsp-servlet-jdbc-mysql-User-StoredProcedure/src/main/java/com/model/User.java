
package com.model;

public class User {
	protected int id;
	protected String name, email, country, contact,gender,areaOfInterest,ImageName;

	public User(String name, String email, String country, String contact,String gender,String areaOfInterest,String ImageName) {
		this.name = name;
		this.email = email;
		this.country = country;
		this.contact = contact;
		this.gender=gender;
		this.areaOfInterest=areaOfInterest;	
		this.ImageName=ImageName;
	}

	public User(int id, String name, String email, String country, String contact,String gender,String areaOfInterest,String ImageName) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
		this.contact = contact;
		this.gender=gender;
		this.areaOfInterest=areaOfInterest;	
		this.ImageName=ImageName;
	}
	
	public User() {
	}
	public String getImageName() {
		return ImageName;
	}

	public void setImageName(String imageName) {
		ImageName = imageName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAreaOfInterest() {
	    return areaOfInterest;
	}
	
	public void setAreaOfInterest(String areaOfInterest) {
	    this.areaOfInterest = areaOfInterest;
	}

	
	

}
