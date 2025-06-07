package com.slibs.slibs.infrastructure;

import java.io.IOException;
import java.util.List;

import com.slibs.slibs.entities.Library;

public interface Parser {
    public String getRepoName();
    public List<Library> getLibraries(int page) throws IOException, InterruptedException;
}
