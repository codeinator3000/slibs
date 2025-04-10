package com.slibs.slibs.entities;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
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
}
