package com.slibs.slibs.controllers;

import java.util.List;
import java.util.Optional;

import com.slibs.slibs.repositories.support.FilterCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slibs.slibs.entities.LibraryDto;
import com.slibs.slibs.entities.License;
import com.slibs.slibs.repositories.support.SearchFilter;
import com.slibs.slibs.services.interfaces.SearchService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;
    private final FilterCreator filterCreator;

    @GetMapping("/{baseRepoName}/{baseLibName}")
    public List<LibraryDto> search(@PathVariable String baseRepoName,
                                   @PathVariable String baseLibName,
                                   @RequestParam int page,
                                   @RequestParam(required = false) List<String> searchRepositories,
                                   @RequestParam(required = false) Optional<String> license) {
        var filters = filterCreator.createFilters(
                license
        );
        return searchService.search(baseRepoName, baseLibName,
                searchRepositories, page, filters);

    }
}
