package com.slibs.slibs.services.interfaces;

import java.util.List;

import com.slibs.slibs.entities.Library;
import com.slibs.slibs.repositories.support.SearchFilter;

public interface SearchService {
    public List<Library> search(String repositoryName, String description, int page);
    public List<Library> search(String repositoryName, String description, int page, SearchFilter filter);
}
