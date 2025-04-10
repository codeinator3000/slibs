package com.slibs.slibs.repositories;

import java.util.List;

import com.slibs.slibs.entities.LibSearch;
import com.slibs.slibs.repositories.support.SearchFilter;

public interface LibSearchRepo {
    void saveOrUpdate(String indexName, LibSearch libSearch);
    void delete(String indexName, LibSearch libSearch);
    void deleteAll(String indexName);
    List<LibSearch> findAllByDescription(String indexName, String description, int page);
    List<LibSearch> findAllByDescription(String indexName, String description, int page, SearchFilter filter);
}
