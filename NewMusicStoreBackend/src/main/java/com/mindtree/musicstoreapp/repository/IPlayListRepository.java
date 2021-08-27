package com.mindtree.musicstoreapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.musicstoreapp.entity.PlayList;

@Repository
public interface IPlayListRepository extends JpaRepository<PlayList, Integer> {

	Optional<PlayList> findByPlaylistName(String playlistName);

}
