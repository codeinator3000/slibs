package com.slibs.slibs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slibs.slibs.entities.Repository;
import java.util.List;

@org.springframework.stereotype.Repository
public interface RepositoryRepo extends JpaRepository<Repository, Integer> {
    Repository findByTitle(String title);
}
