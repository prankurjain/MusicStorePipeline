package com.mindtree.musicstoreapp.dto;

public class SongDTO {

	private int songId;
	private String songName;
	public SongDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SongDTO(int songId, String songName) {
		super();
		this.songId = songId;
		this.songName = songName;
	}
	public int getSongId() {
		return songId;
	}
	public void setSongId(int songId) {
		this.songId = songId;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	
	
	
	
}
