package com.slibs.slibs.entities;

@lombok.Data
public class Library {
    private int id;
    private String title;
    private String descString;
    private String author;
    private String url;
    private Repository repository;
    private License license;
}
