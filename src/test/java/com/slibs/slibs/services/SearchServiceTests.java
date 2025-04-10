package com.slibs.slibs.services;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.slibs.slibs.entities.LibSearch;
import com.slibs.slibs.entities.Library;
import com.slibs.slibs.repositories.LibSearchRepo;
import com.slibs.slibs.repositories.LibraryRepo;
import com.slibs.slibs.repositories.support.SearchFilter;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTests {
    @Mock
    private LibSearchRepo libSearchRepo;
    @Mock
    private LibraryRepo libraryRepo;
    @Test
    void whenNormalSearch_thenSearch_returnsLibraries() {
        // given
        String repositoryName = "repo1";
        String libName = "lib1";
        List<String> repositories = List.of("repo1", "repo2");
        int page = 0;
        SearchFilter filter = new SearchFilter();
        
        // Mocking the repository response
        List<LibSearch> mockLibSearchResults = new ArrayList<>();
        LibSearch ls = new LibSearch();
        ls.setId(1);
        ls.setDescription(libName);
        mockLibSearchResults.add(ls);
        
        when(libSearchRepo.findByDescription(repositoryName, libName)).thenReturn(ls);
        when(libSearchRepo.findAllByDescription(repositoryName, libName, page, filter))
            .thenReturn(mockLibSearchResults);

        // Mocking the library response
        List<Library> mockLibResults = new ArrayList<>();
        Library lib = new Library();
        mockLibResults.add(lib);
        when(libraryRepo.findAllById(mockLibSearchResults.stream().map(LibSearch::getId).toList()))
            .thenReturn(mockLibResults);

        // when
        SearchServiceImpl searchService = new SearchServiceImpl(libSearchRepo, libraryRepo);
        List<Library> result = searchService.search(repositoryName, libName, repositories, page, filter);

        // then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(mockLibSearchResults.size(), result.size());
        assertEquals(result.get(0), mockLibResults.get(0));
    }
}
