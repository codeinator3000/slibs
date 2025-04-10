package com.slibs.slibs.entities;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Класс, представляющий лицензию библиотеки.
 * Используется для хранения информации о лицензии библиотеки.
 */
@Data
public class License {
    @Id
    private int id;
    private String name;
    @ManyToOne
    private List<Library> libraries;
}
