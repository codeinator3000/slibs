package com.slibs.slibs.entities;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Класс, представляющий лицензию библиотеки.
 * Используется для хранения информации о лицензии библиотеки.
 */
public class License {
    @Id
    private int id;
    private String name;
    @ManyToOne
    private List<Library> libraries;

    public License(int id, String name, List<Library> libraries) {
        this.id = id;
        this.name = name;
        this.libraries = libraries;
    }

    
    public License() {
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Library> getLibraries() {
        return libraries;
    }
    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }

    
}
