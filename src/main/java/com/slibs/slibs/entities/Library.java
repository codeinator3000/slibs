package com.slibs.slibs.entities;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
/**
 * Класс, представляющий библиотеку в системе.
 * Используется для хранения всей основной информации о библиотеке.
 */
@Table(name = "library")
public class Library {
    @Id
    private int id;
    private String title;
    private String descString;
    private String author;
    private String url;
    @OneToMany
    private Repository repository;
    @OneToMany
    private License license;
    
    public Library(int id, String title, String descString, String author, String url, Repository repository,
            License license) {
        this.id = id;
        this.title = title;
        this.descString = descString;
        this.author = author;
        this.url = url;
        this.repository = repository;
        this.license = license;
    }
    

    public Library() {
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

    public String getDescString() {
        return descString;
    }

    public void setDescString(String descString) {
        this.descString = descString;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    
}
