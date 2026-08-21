package com.slibs.slibs.entities;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Класс, представляющий облегченную версию библиотеки.
 * Используется только для поиска по описанию и лицензии.
 * Помимо этого хранит идентификатор на информацию о библиотеке в основной базе данных.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibSearch {
    @Field(name = "main_id")
    private int mainId;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Constant_Keyword, name = "license")
    private String license;

    public LibSearch(Library library) {
        this.license = library.getLicense().getName();
        this.description = library.getDescription() + " " + library.getKeywords();
        this.mainId = library.getId();
    }
}
