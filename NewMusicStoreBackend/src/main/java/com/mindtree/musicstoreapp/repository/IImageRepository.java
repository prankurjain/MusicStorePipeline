package com.mindtree.musicstoreapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.musicstoreapp.entity.Image;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {

	Optional<Image> findByName(String imageName);

}
