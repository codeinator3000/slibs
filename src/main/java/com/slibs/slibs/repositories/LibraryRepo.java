package com.slibs.slibs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slibs.slibs.entities.Library;

public interface LibraryRepo extends JpaRepository<Library, Integer> {
    
}
