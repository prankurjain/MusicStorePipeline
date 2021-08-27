package com.mindtree.musicstoreapp.dto;

import java.util.Arrays;

public class PlayListDTO {

	private String playlistName;
	private int[]songs;
	public PlayListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlayListDTO(String playlistName, int[] songs) {
		super();
		this.playlistName = playlistName;
		this.songs = songs;
	}
	public String getPlaylistName() {
		return playlistName;
	}
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	public int[] getSongs() {
		return songs;
	}
	public void setSongs(int[] songs) {
		this.songs = songs;
	}
	@Override
	public String toString() {
		return "PlayListDTO [playlistName=" + playlistName + ", songs=" + Arrays.toString(songs) + "]";
	}
	
}
