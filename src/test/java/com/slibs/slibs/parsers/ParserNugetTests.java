package com.slibs.slibs.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.slibs.slibs.entities.Library;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.slibs.slibs.infrastructure.parsers.ParserNuget;
import com.slibs.slibs.infrastructure.support.ApiClient;
import com.slibs.slibs.infrastructure.support.LibraryDto;
import com.slibs.slibs.infrastructure.support.LibraryMapper;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ParserNugetTests {
    @Mock
    private ApiClient mockClient;
    @Mock
    private LibraryMapper mockLibraryMapper;
    @Test
    void whenNormalSearch_thenSearch_returnsLibraries() {
        int size = 2;
        List<LibraryDto> libsDto = new ArrayList<>();
        List<Library> libs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            libsDto.add(new LibraryDto());
            libs.add(new Library());
        }
        when(mockClient.getLibsDto("nuget", 0, size))
            .thenReturn(libsDto);
        when(mockLibraryMapper.toLibraryList(libsDto))
            .thenReturn(libs);
        
        var parser = new ParserNuget(mockClient, mockLibraryMapper);
        
        var res = parser.getLibraries(0, size);
        assertNotNull(res);
        assertEquals(2, res.size());
    }

    @Test
    void whenEmptySearch_thenSearch_returnsEmptyList() {
        List<LibraryDto> libsDto = new ArrayList<>();
        List<Library> libs = new ArrayList<>();

        when(mockClient.getLibsDto("nuget", 0, 0)).thenReturn(libsDto);
        when(mockLibraryMapper.toLibraryList(libsDto)).thenReturn(libs);

        var parser = new ParserNuget(mockClient, mockLibraryMapper);
        var res = parser.getLibraries(0, 0);

        assertNotNull(res);
        assertEquals(0, res.size());
    }

    @Test
    void whenLargeSearch_thenSearch_returnsLibraries() {
        int size = 100;
        List<LibraryDto> libsDto = new ArrayList<>();
        List<Library> libs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            libsDto.add(new LibraryDto());
            libs.add(new Library());
        }

        when(mockClient.getLibsDto("nuget", 0, size)).thenReturn(libsDto);
        when(mockLibraryMapper.toLibraryList(libsDto)).thenReturn(libs);

        var parser = new ParserNuget(mockClient, mockLibraryMapper);
        var res = parser.getLibraries(0, size);

        assertNotNull(res);
        assertEquals(size, res.size());
    }
}
