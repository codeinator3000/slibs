package com.slibs.slibs.services.interfaces;

import java.util.List;

import co.elastic.clients.elasticsearch.snapshot.Repository;

public interface MainDbService {
    public List<Repository> getAllRepositories();
}
