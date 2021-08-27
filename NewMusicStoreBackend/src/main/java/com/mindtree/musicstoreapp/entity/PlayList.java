package com.mindtree.musicstoreapp.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="playlistId")
public class PlayList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int playlistId;
	
	@Column(unique = true)
	private String playlistName;
	
	@ManyToMany
	@JoinTable(name = "playlist_song",
	joinColumns = {
		 @JoinColumn(name="playlistId",referencedColumnName = "playlistId") },
	inverseJoinColumns= {
	 @JoinColumn(name = "songId",referencedColumnName = "songId") } ,uniqueConstraints = { @UniqueConstraint(columnNames = {"playlistId","songId"})})
	private List<Song>songs;

	public PlayList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlayList(int playlistId, String playlistName, List<Song> songs) {
		super();
		this.playlistId = playlistId;
		this.playlistName = playlistName;
		this.songs = songs;
	}

	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}




	
	
}
