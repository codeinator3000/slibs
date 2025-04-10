package com.slibs.slibs.entities;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Класс, представляющий репозиторий в системе.
 * Используется для хранения данных о репозитории библиотеки.
 */
@Data
public class Repository {
    @Id
    private int id;
    private String title;
    @ManyToOne
    private List<Library> libraries;
}
