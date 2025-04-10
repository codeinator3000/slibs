package com.slibs.slibs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slibs.slibs.entities.Repository;

public interface RepositoryRepo extends JpaRepository<Repository, Integer> {
    
}
