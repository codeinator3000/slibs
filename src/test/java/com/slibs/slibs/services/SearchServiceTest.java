package com.slibs.slibs.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.slibs.slibs.entities.LibSearch;
import com.slibs.slibs.entities.Library;
import com.slibs.slibs.infrastructure.ParserManager;
import com.slibs.slibs.persistence.searchdb.filters.LicenseFilter;
import com.slibs.slibs.repositories.LibSearchRepo;
import com.slibs.slibs.repositories.LibraryRepo;
import com.slibs.slibs.repositories.support.FilterCreator;
import com.slibs.slibs.repositories.support.SearchFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private LibSearchRepo libSearchRepo;
    
    @Mock
    private LibraryRepo libraryRepo;
    
    @Mock
    private ParserManager parserManager;
    
    @InjectMocks
    private SearchServiceImpl searchService;
    
    private final LibSearch testLibSearch = new LibSearch();
    private final Library testLibrary = new Library();

    @BeforeEach
    void setup() {
        testLibSearch.setMainId(1);
        testLibrary.setId(1);
    }

    @Test
    void whenSearch_whenLibNotFound_shouldReturnEmptyList() {
        when(libSearchRepo.findByDescription("repo", "missing")).thenReturn(null);
        
        List<Library> result = searchService.search("repo", "missing", List.of(), 0);
        
        assertTrue(result.isEmpty());
    }

    @Test
    void whenSearch_withEmptyRepositories_shouldUseAllRepos() {
        when(libSearchRepo.findByDescription("repo", "found")).thenReturn(testLibSearch);
        when(parserManager.getRepoNames()).thenReturn(List.of("repo1", "repo2"));
        when(libSearchRepo.findAllByDescription("repo1", testLibSearch.getDescription(), 0, null))
            .thenReturn(List.of(testLibSearch));
        when(libSearchRepo.findAllByDescription("repo2", testLibSearch.getDescription(), 0, null))
            .thenReturn(Collections.emptyList());
        when(libraryRepo.findAllById(anyList())).thenReturn(List.of(testLibrary));

        List<Library> result = searchService.search("repo", "found", Collections.emptyList(), 0);
        
        assertEquals(1, result.size());
        verify(parserManager).getRepoNames();
    }

    @Test
    void whenSearch_withSpecificRepositories_shouldUseOnlySpecified() {
        when(libSearchRepo.findByDescription("repo", "found")).thenReturn(testLibSearch);
        when(libSearchRepo.findAllByDescription("customRepo", testLibSearch.getDescription(), 0, null))
            .thenReturn(List.of(testLibSearch));
        when(libraryRepo.findAllById(anyList())).thenReturn(List.of(testLibrary));

        List<Library> result = searchService.search("repo", "found", List.of("customRepo"), 0);
        
        assertEquals(1, result.size());
        verify(libSearchRepo).findAllByDescription("customRepo", testLibSearch.getDescription(), 0, null);
    }

    @Test
    void whenSearch_withFilters_shouldPassFiltersToRepo() {
        var filter = new LicenseFilter("MIT");
        List<SearchFilter> filters = List.of(filter);
        when(libSearchRepo.findByDescription("repo", "found")).thenReturn(testLibSearch);
        when(libSearchRepo.findAllByDescription("customRepo", testLibSearch.getDescription(), 0, filters))
            .thenReturn(List.of(testLibSearch));
        when(libraryRepo.findAllById(anyList())).thenReturn(List.of(testLibrary));

        List<Library> result = searchService.search("repo", "found", List.of("customRepo"), 0, filters);
        
        assertEquals(1, result.size());
        verify(libSearchRepo).findAllByDescription("customRepo", testLibSearch.getDescription(), 0, filters);
    }

    @Test
    void whenSearch_shouldMapLibSearchesToLibraries() {
        LibSearch libSearch1 = new LibSearch();
        libSearch1.setMainId(1);
        LibSearch libSearch2 = new LibSearch();
        libSearch2.setMainId(2);
        
        Library library1 = new Library();
        library1.setId(1);
        Library library2 = new Library();
        library2.setId(2);
        
        when(libSearchRepo.findByDescription("repo", "found")).thenReturn(testLibSearch);
        when(libSearchRepo.findAllByDescription("repo", testLibSearch.getDescription(), 0, null))
            .thenReturn(List.of(libSearch1, libSearch2));
        when(libraryRepo.findAllById(List.of(1, 2))).thenReturn(List.of(library1, library2));

        List<Library> result = searchService.search("repo", "found", List.of("repo"), 0);
        
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
    }
}