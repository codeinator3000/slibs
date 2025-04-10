package com.slibs.slibs.entities;

import java.util.List;

@lombok.Data
public class Repository {
    private int id;
    private String title;
    private List<Library> libraries;
}
