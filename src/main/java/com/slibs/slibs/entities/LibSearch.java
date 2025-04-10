package com.slibs.slibs.entities;

import org.springframework.data.elasticsearch.annotations.Field;

import jakarta.persistence.Id;
import lombok.Data;
/**
 * Класс, представляющий облегченную версию библиотеки.
 * Используется только для поиска по описанию и лицензии.
 * Помимо этого хранит идентификатор на информацию о библиотеке в основной базе данных.
 */
public class LibSearch {
    @Id
    private int id;
    @Field(name = "main_id")
    private int mainId;
    private String description;
    private String license;

    public LibSearch(int id, int mainId, String description, String license) {
        this.id = id;
        this.mainId = mainId;
        this.description = description;
        this.license = license;
    }
    

    public LibSearch() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    
}
