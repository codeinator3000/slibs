package com.slibs.slibs.infrastructure.support;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDto {
    @JsonAlias("name")
    private String title;
    private String description;
    @JsonAlias("package_manager_url")
    private String url;
    @JsonAlias("platform")
    private String repository;
    @JsonAlias("repository_license")
    private String license;
    private List<String> keywords;
    private String language;
}
