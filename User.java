package com.mstore.user;

public class User {
	
	private String Email;
	private String Password;
	private String fName;
	private String lName;
	private String Address;
	private String Phone;
	private String Role;
	private String DOB;
	
	public User(String email, String password, String fName, String lName, String address, String phone, String role, String dob) {
        this.Email = email;
        this.Password = password;
        this.fName = fName;
        this.lName = lName;
        this.Address = address;
        this.Phone = phone;
        this.Role = role;
        this.DOB = dob;
    }
	

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

}
