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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.musicstoreapp.dto.PlayListDTO;
import com.mindtree.musicstoreapp.entity.PlayList;
import com.mindtree.musicstoreapp.exception.MainException;
import com.mindtree.musicstoreapp.exception.service.ServiceException;
import com.mindtree.musicstoreapp.service.IPlayListService;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlayListController {

	
	@Autowired
	IPlayListService playListService;
	
	@PostMapping(value = "/addplaylist")
	ResponseEntity<String>addPlayList(@RequestBody PlayListDTO playList) throws MainException
	{
		String s=playListService.addPlayList(playList);
		
		return new ResponseEntity<String>(s,HttpStatus.ACCEPTED);
		
	}
	@GetMapping(value="/displayplaylist")
	ResponseEntity<String []>getPlayList(@RequestParam String playlistName) throws MainException
	{
		String s[]=playListService.getPlayList(playlistName);
		return new ResponseEntity<String[]>(s,HttpStatus.ACCEPTED);
	}
}
