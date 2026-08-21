package com.slibs.slibs.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Класс, представляющий лицензию библиотеки.
 * Используется для хранения информации о лицензии библиотеки.
 */
@Entity
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(unique = true)
    private String name;

    public License(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public License(int id) {
        this.id = id;
    }

    public License(String name) {
        this.name = name;
    }

    
    public License() {
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
}
