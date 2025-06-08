package com.slibs.slibs.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий репозиторий в системе.
 * Используется для хранения данных о репозитории библиотеки.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(unique = true)
    private String title;
    @Column(unique = true)
    private String lang;
    public Repository(int id) {
        this.id = id;
    }
    public Repository(String title, String lang) {
        this.title = title;
        this.lang = lang;
    }
}
