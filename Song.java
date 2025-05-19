package com.mstore.song;

public class Song {
	private String Title;
	private String Artist;
	private String Album;
	private String Genre;
	private String ReleaseDate;
	private String Duration;
	private String link;
	private Double price;
	
	public Song(String title, String artist, String album, String genre, String releaseDate, String duration,
			String link, String price) {
		super();
		Title = title;
		Artist = artist;
		Album = album;
		Genre = genre;
		ReleaseDate = releaseDate;
		Duration = duration;
		try {
			this.price = Double.valueOf(price);
		} catch (Exception e) {
			this.price = 0.0;
		}
		this.link = link;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getArtist() {
		return Artist;
	}

	public void setArtist(String artist) {
		Artist = artist;
	}

	public String getAlbum() {
		return Album;
	}

	public void setAlbum(String album) {
		Album = album;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getReleaseDate() {
		return ReleaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		ReleaseDate = releaseDate;
	}

	public String getDuration() {
		return Duration;
	}

	public void setDuration(String duration) {
		Duration = duration;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
