package com.mstore.artist;

public class Artist {
	private String Email;
	private String Password;
	private String fName;
	private String lName;
	private String artistBio;
	
	public Artist(String email, String password, String fName, String lName, String artistBio) {
		Email = email;
		Password = password;
		this.fName = fName;
		this.lName = lName;
		this.artistBio = artistBio;
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
	
	public String getArtistBio() {
		return artistBio;
	}
	
	public void setArtistBio(String artistBio) {
		this.artistBio = artistBio;
	}
}
