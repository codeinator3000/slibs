package com.slibs.slibs.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.slibs.slibs.entities.Library;

public class ParserManager {
    private final Map<String, Parser> parsers = new HashMap<>();

    public ParserManager(List<Parser> parsers) {
        parsers.forEach(parser -> this.parsers.put(parser.getRepoName(), parser));
    }

    public List<Library> getLibraries(String repoName, int page) {
        return parsers.get(repoName).getLibraries(page);
    }
}
