package com.slibs.slibs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.elastic.clients.elasticsearch.license.License;

public interface LicenseRepo extends JpaRepository<License, Integer> {
    
}
