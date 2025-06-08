package com.slibs.slibs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.slibs.slibs.infrastructure.ParserManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.slibs.slibs.entities.LibSearch;
import com.slibs.slibs.entities.Library;
import com.slibs.slibs.repositories.LibSearchRepo;
import com.slibs.slibs.repositories.LibraryRepo;
import com.slibs.slibs.repositories.support.SearchFilter;
import com.slibs.slibs.services.interfaces.SearchService;
import com.slibs.slibs.services.support.LibraryResponse;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final LibSearchRepo libSearchRepo; 
    private final LibraryRepo libraryRepo;
    private final ParserManager parserManager;

    @Override
    public List<Library> search(String repositoryName, String libName, List<String> repositories, int page) {
        return search(repositoryName, libName, repositories, page, null);
    }

    @Override
    public List<Library> search(String repositoryName, String libName, List<String> repositories,
                                int page, List<SearchFilter> filters) {
        // Поиск указанной библиотеки
        LibSearch libSearch = libSearchRepo.findByDescription(repositoryName, libName);
        if (libSearch == null) {
            return new ArrayList<>();
        }

        // Если репозиториев нет, то поиск идет сразу по всем
        if (repositories == null || repositories.isEmpty()) {
            repositories = parserManager.getRepoNames();
        }

        List<LibSearch> libSearches = getLibSearchesFromRepos(repositories,
                libSearch.getDescription(), page, filters);

        var libraries = getLibrariesFromLibSearches(libSearches);

        return libraries;
    }

    private List<LibSearch> getLibSearchesFromRepos(List<String> repos, String description,
                                                    int page, List<SearchFilter> filters) {
        List<LibSearch> libSearches = new ArrayList<>();

        // Получаем все похожие библиотеки среди указанных репозиториев
        for (String repoName : repos) {
            libSearches = libSearchRepo.findAllByDescription(repoName, description, page, filters);
        }
        return libSearches;
    }

    private List<Library> getLibrariesFromLibSearches(List<LibSearch> libSearches) {
        // Получение списка идентификаторов аналогов
        List<Integer> ids = libSearches.stream()
                .map(LibSearch::getMainId)
                .toList();
        // Получение данных для списка аналогов
        List<Library> libraries = libraryRepo.findAllById(ids);

        return libraries;
    }
}
