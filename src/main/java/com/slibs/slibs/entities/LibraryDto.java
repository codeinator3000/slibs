package com.slibs.slibs.entities;

import lombok.Data;

@Data
public class LibraryDto {
    private String title;
    private String description;
    private String author;
    private String url;
    private String repository;
    private String license;
    public LibraryDto(Library lib) {
        this.title = lib.getTitle();
        this.description = lib.getDescription();
        this.author = lib.getAuthor();
        this.url = lib.getUrl();
        this.repository = lib.getRepository().getTitle();
        this.license = lib.getLicense().getName();
    }
}
