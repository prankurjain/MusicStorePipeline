package com.mindtree.musicstoreapp.service;

import java.util.List;

import com.mindtree.musicstoreapp.dto.SongDTO;
import com.mindtree.musicstoreapp.entity.Song;
import com.mindtree.musicstoreapp.exception.service.ServiceException;

public interface ISongService {

	String addSong(Song song) throws ServiceException;

	List<SongDTO> displaySong() throws ServiceException;

}
