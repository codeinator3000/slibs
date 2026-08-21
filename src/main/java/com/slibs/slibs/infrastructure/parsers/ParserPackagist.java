package com.slibs.slibs.infrastructure.parsers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.slibs.slibs.entities.Library;
import com.slibs.slibs.infrastructure.Parser;
import com.slibs.slibs.infrastructure.support.ApiClient;
import com.slibs.slibs.infrastructure.support.LibraryMapper;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ParserPackagist implements Parser {
    private final String repoName = "packagist";
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
