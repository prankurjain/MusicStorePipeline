package com.mindtree.musicstoreapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="songId")
public class Song implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int songId;
	@Column(unique = true)
	private String songName;
	private String songArtist;
	private String songLocation;
	private String songAlbum;
	private int songDuration;
	private String songTags;
	
	@ManyToMany(mappedBy = "songs")
	private List<PlayList>playLists;

	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Song(int songId, String songName, String songArtist, String songLocation, String songAlbum, int songDuration,
			String songTags, List<PlayList> playLists) {
		super();
		this.songId = songId;
		this.songName = songName;
		this.songArtist = songArtist;
		this.songLocation = songLocation;
		this.songAlbum = songAlbum;
		this.songDuration = songDuration;
		this.songTags = songTags;
		this.playLists = playLists;
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

	public String getSongArtist() {
		return songArtist;
	}

	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}

	public String getSongLocation() {
		return songLocation;
	}

	public void setSongLocation(String songLocation) {
		this.songLocation = songLocation;
	}

	public String getSongAlbum() {
		return songAlbum;
	}

	public void setSongAlbum(String songAlbum) {
		this.songAlbum = songAlbum;
	}

	public int getSongDuration() {
		return songDuration;
	}

	public void setSongDuration(int songDuration) {
		this.songDuration = songDuration;
	}

	public String getSongTags() {
		return songTags;
	}

	public void setSongTags(String songTags) {
		this.songTags = songTags;
	}

	public List<PlayList> getPlayLists() {
		return playLists;
	}

	public void setPlayLists(List<PlayList> playLists) {
		this.playLists = playLists;
	}
	
	
	
}
