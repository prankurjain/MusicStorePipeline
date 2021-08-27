package com.mindtree.musicstoreapp.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.musicstoreapp.dto.PlayListDTO;
import com.mindtree.musicstoreapp.entity.PlayList;
import com.mindtree.musicstoreapp.entity.Song;
import com.mindtree.musicstoreapp.exception.service.PlayListAlreadyExistException;
import com.mindtree.musicstoreapp.exception.service.PlayListDoesNotExistException;
import com.mindtree.musicstoreapp.exception.service.ServiceException;
import com.mindtree.musicstoreapp.repository.IPlayListRepository;
import com.mindtree.musicstoreapp.service.IPlayListService;

@Service
@Transactional
public class PlayListServiceImpl implements IPlayListService {

	@Autowired
	IPlayListRepository playlistRepository;
	
	@Override
	public String addPlayList(PlayListDTO playList) throws ServiceException {
		Optional<PlayList>playlists=playlistRepository.findByPlaylistName(playList.getPlaylistName());
		if(playlists.isPresent())
			throw new PlayListAlreadyExistException("This PlayList is already Exist");
		System.out.println(playList.toString());
		System.out.println(playList.getSongs().length);
		PlayList p=new PlayList();
		p.setPlaylistName(playList.getPlaylistName());
		int a[]=playList.getSongs();
		List<Song> songs=new ArrayList<Song>();
		for(int i=0; i<a.length; i++)
		{
			Song song=new Song();	
			song.setSongId(a[i]);
			songs.add(song);
		}
		p.setSongs(songs);
		playlistRepository.save(p);
		return "Successfully Added";
	}

	@Override
	public String[] getPlayList(String playlistName) throws ServiceException {
		PlayList playlist=playlistRepository.findByPlaylistName(playlistName).orElseThrow(()->new PlayListDoesNotExistException("play list does not exist"));
		List<Song>songs=playlist.getSongs();
		String s[]=new String[songs.size()];
		for(int i=0; i<songs.size(); i++)
		{
			s[i]=songs.get(i).getSongName();
		}
		
	
		
		return s;
	}

}
