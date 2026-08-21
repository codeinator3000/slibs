package com.slibs.slibs.infrastructure.parsers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.slibs.slibs.entities.Library;
import com.slibs.slibs.entities.License;
import com.slibs.slibs.entities.Repository;
import com.slibs.slibs.infrastructure.Parser;
import com.slibs.slibs.infrastructure.support.ApiClient;
import com.slibs.slibs.infrastructure.support.LibraryMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ParserNpm implements Parser {
    private final String repoName = "npm";
    private final ApiClient apiClient;
    private final LibraryMapper libraryMapper;

    @Override
    public String getRepoName() {
        return repoName;
    }

    @Override
    public List<Library> getLibraries(int page, int pageSize){
        var lisbsDto = apiClient.getLibsDto(repoName, page, pageSize);

        return libraryMapper.toLibraryList(lisbsDto);

    }
}
