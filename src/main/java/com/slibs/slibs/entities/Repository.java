package com.slibs.slibs.entities;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Класс, представляющий репозиторий в системе.
 * Используется для хранения данных о репозитории библиотеки.
 */
public class Repository {
    @Id
    private int id;
    private String title;
    @ManyToOne
    private List<Library> libraries;

    public Repository(int id, String title, List<Library> libraries) {
        this.id = id;
        this.title = title;
        this.libraries = libraries;
    }

    
    public Repository() {
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<Library> getLibraries() {
        return libraries;
    }
    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }
    
}
