package com.slibs.slibs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slibs.slibs.entities.License;
import java.util.List;


@Repository
public interface LicenseRepo extends JpaRepository<License, Integer> {
    List<License> findByName(String name);
}
