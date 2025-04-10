package com.slibs.slibs.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.slibs.slibs.entities.LibSearch;
import com.slibs.slibs.entities.Library;
import com.slibs.slibs.repositories.LibSearchRepo;
import com.slibs.slibs.repositories.LibraryRepo;
import com.slibs.slibs.repositories.support.SearchFilter;
import com.slibs.slibs.services.interfaces.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
    private final LibSearchRepo libSearchRepo; 
    private final LibraryRepo libraryRepo;

    public SearchServiceImpl(LibSearchRepo libSearchRepo, LibraryRepo libraryRepo) {
        this.libSearchRepo = libSearchRepo;
        this.libraryRepo = libraryRepo;
    }

    @Override
    public List<Library> search(String repositoryName, String libName, List<String> repositories, int page) {
        return search(repositoryName, libName, repositories, page, null);
    }

    @Override
    public List<Library> search(String repositoryName, String libName, List<String> repositories, int page,
            SearchFilter filter) {
        // Поиск указанной библиотеки
        LibSearch libSearch = libSearchRepo.findByDescription(repositoryName, libName);
        // Поиск аналогов библиотеки
        List<LibSearch> libSearches = libSearchRepo
            .findAllByDescription(repositoryName, libSearch.getDescription(), page, filter);
        // Получение списка идентификаторов аналогов
        List<Integer> ids = libSearches.stream()
            .map(LibSearch::getId)
            .toList();
        // Получение данных для списка аналогов
        List<Library> libraries = libraryRepo.findAllById(ids);
        
        return libraries;
    }
}
