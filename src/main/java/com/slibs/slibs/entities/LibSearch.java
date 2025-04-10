package com.slibs.slibs.entities;

import org.springframework.data.elasticsearch.annotations.Field;

import jakarta.persistence.Id;
import lombok.Data;
/**
 * Класс, представляющий облегченную версию библиотеки.
 * Используется только для поиска по описанию и лицензии.
 * Помимо этого хранит идентификатор на информацию о библиотеке в основной базе данных.
 */
@Data
public class LibSearch {
    @Id
    private int id;
    @Field(name = "main_id")
    private int mainId;
    private String description;
    private String license;
}
