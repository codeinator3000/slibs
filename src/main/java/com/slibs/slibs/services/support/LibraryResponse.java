package com.slibs.slibs.services.support;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LibraryResponse {
    private String title;
    private String description;
    private String url;
    private String repository;
    private String license;
}
