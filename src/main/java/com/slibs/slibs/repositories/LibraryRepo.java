package com.slibs.slibs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.slibs.slibs.entities.Library;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface LibraryRepo extends JpaRepository<Library, Integer> {
    Library findByUrl(String url);
}
