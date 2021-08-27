package com.mindtree.musicstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.musicstoreapp.dto.SongDTO;
import com.mindtree.musicstoreapp.entity.Song;
import com.mindtree.musicstoreapp.exception.MainException;
import com.mindtree.musicstoreapp.service.ISongService;

@RestController
@CrossOrigin("*")
@RequestMapping("/song")
public class SongController {

	@Autowired
	ISongService songService;
	
	@PostMapping(value = "/addsong")
	ResponseEntity<String>addSong(@RequestBody Song song) throws MainException
	{
		String s=songService.addSong(song);
		return new ResponseEntity<String>(s,HttpStatus.ACCEPTED);
		
	}
	@GetMapping(value = "/displaysong")
	ResponseEntity<List<SongDTO>>displaySong() throws MainException
	{
		List<SongDTO>song=songService.displaySong();
		return new ResponseEntity<List<SongDTO>>(song,HttpStatus.ACCEPTED);
		
	}
	
}
