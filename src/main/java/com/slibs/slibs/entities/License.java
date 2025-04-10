package com.slibs.slibs.entities;

import java.util.List;

@lombok.Data
public class License {
    private int id;
    private String name;
    private List<Library> libraries;
}
