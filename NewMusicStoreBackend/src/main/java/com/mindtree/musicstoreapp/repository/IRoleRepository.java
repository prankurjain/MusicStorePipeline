package com.mindtree.musicstoreapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.musicstoreapp.entity.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(String roleName);

}
