package com.slibs.slibs.entities;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
/**
 * Класс, представляющий библиотеку в системе.
 * Используется для хранения всей основной информации о библиотеке.
 */
@Entity
@Table(name = "library")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String title;
    @Column(name = "description", length = 1024)
    private String description;
    private String author;
    private String url;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Repository repository;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private License license;
    
    public Library(int id, String title, String description, String author, String url, Repository repository,
            License license) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.url = url;
        this.repository = repository;
        this.license = license;
    }
    public void update(Library lib) {
        this.title = lib.getTitle();
        this.description = lib.getDescription();
        this.author = lib.getAuthor();
        this.url = lib.getUrl();
        if (lib.getRepository().getTitle() != this.repository.getTitle()) {
            this.repository = lib.getRepository();
        }
        if (lib.getLicense().getName() != this.license.getName()) {
            this.license = lib.getLicense();
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String descString) {
        this.description = descString;
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
