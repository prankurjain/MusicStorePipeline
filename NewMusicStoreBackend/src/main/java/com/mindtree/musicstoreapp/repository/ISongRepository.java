package com.mindtree.musicstoreapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.musicstoreapp.entity.Song;

@Repository
public interface ISongRepository extends JpaRepository<Song, Integer> {

	Optional<Song> findBySongName(String songName);

}
