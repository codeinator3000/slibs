package com.slibs.slibs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slibs.slibs.entities.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface RepositoryRepo extends JpaRepository<Repository, Integer> {
//    @Query(value =  "SELECT * FROM repository AS r " +
//                    "WHERE r.title LIKE :title " +
//                    "FETCH FIRST 1 ROW ONLY;", nativeQuery = true)
//    Repository findOneByTitle(@Param("title") String title);
    List<Repository> findByTitle(String title);
}
