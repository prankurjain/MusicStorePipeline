package com.mindtree.musicstoreapp.service;



import com.mindtree.musicstoreapp.dto.PlayListDTO;

import com.mindtree.musicstoreapp.exception.service.ServiceException;

public interface IPlayListService {

	String addPlayList(PlayListDTO playList) throws ServiceException;
	String [] getPlayList(String playlistName) throws ServiceException;
}
