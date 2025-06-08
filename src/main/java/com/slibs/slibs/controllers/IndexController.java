package com.slibs.slibs.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.slibs.slibs.entities.License;
import com.slibs.slibs.entities.Repository;
import com.slibs.slibs.services.interfaces.IndexService;

import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/index")
@AllArgsConstructor
public class IndexController {
    private final IndexService indexService;

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean updateIndex() {
        return indexService.updateIndex();
    }

    @GetMapping("/repositories")
    public List<Repository> getAllRepositories() {
        return indexService.getAllRepositories();
    }

    @GetMapping("/licenses")
    public List<String> getAllLicense() {
        return indexService.getAllLicenses().stream().map(License::getName).toList();
    }
    
}
