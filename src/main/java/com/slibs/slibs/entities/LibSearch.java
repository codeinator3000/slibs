package com.slibs.slibs.entities;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
/**
 * Класс, представляющий облегченную версию библиотеки.
 * Используется только для поиска по описанию и лицензии.
 * Помимо этого хранит идентификатор на информацию о библиотеке в основной базе данных.
 */
public class LibSearch {
    @Field(name = "main_id")
    private int mainId;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Constant_Keyword, name = "license")
    private String license;

    public LibSearch(int mainId, String description, String license) {
        this.mainId = mainId;
        this.description = description;
        this.license = license;
    }

    public LibSearch(Library library) {
        this.license = library.getLicense().getName();
        this.description = library.getDescription();
        this.mainId = library.getId();
    }
    

    public LibSearch() {
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
