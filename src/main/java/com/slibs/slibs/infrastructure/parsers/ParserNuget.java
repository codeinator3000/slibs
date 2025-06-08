package com.slibs.slibs.infrastructure.parsers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.slibs.slibs.entities.Library;
import com.slibs.slibs.infrastructure.Parser;
import com.slibs.slibs.infrastructure.support.ApiClient;
import com.slibs.slibs.infrastructure.support.LibraryMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ParserNuget implements Parser{
    private final String repoName = "nuget";
    private final ApiClient client;
    private final LibraryMapper libraryMapper;

    public String getRepoName() {
        return repoName;
    }

    @Override
    public List<Library> getLibraries(int page, int pageSize) {
        var libsDto = client.getLibsDto(repoName, page, pageSize);

        return libraryMapper.toLibraryList(libsDto);
    }


}
