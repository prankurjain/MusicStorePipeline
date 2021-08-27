package com.mindtree.musicstoreapp.serviceimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.musicstoreapp.dto.SongDTO;
import com.mindtree.musicstoreapp.entity.Song;
import com.mindtree.musicstoreapp.exception.service.ListIsEmptyException;
import com.mindtree.musicstoreapp.exception.service.ServiceException;
import com.mindtree.musicstoreapp.exception.service.SongAlreadyExistException;
import com.mindtree.musicstoreapp.repository.ISongRepository;
import com.mindtree.musicstoreapp.service.ISongService;

@Service
@Transactional
public class SongServiceImpl implements ISongService{

	@Autowired
	ISongRepository songRepository;
	
	
	@Override
	public String addSong(Song song) throws ServiceException {
		
		Optional<Song>so=songRepository.findBySongName(song.getSongName());
		if(so.isPresent())
			throw new SongAlreadyExistException("This song is already Exist");
		songRepository.save(song);
		
		return "Successfully Added";
	}


	@Override
	public List<SongDTO> displaySong() throws ServiceException {
		List<Song>songs=songRepository.findAll();
		List<SongDTO>songdto=new ArrayList<SongDTO>();
		if(songs.isEmpty())
			throw new ListIsEmptyException("No song in the database");
		Iterator<Song>itr=songs.iterator();
		Song song;
		while(itr.hasNext())
		{
			song=itr.next();
			songdto.add(new SongDTO(song.getSongId(), song.getSongName()));
		}
		
		return songdto;
	}

}
