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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Класс, представляющий библиотеку в системе.
 * Используется для хранения всей основной информации о библиотеке.
 */
@Entity
@Table(name = "library")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String title;
    @Column(name = "description", length = 4096)
    private String description;
    @Column(name = "keywords", length = 4096)
    private String keywords;
    private String url;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Repository repository;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private License license;
    
    public void update(Library lib) {
        this.title = lib.getTitle();
        this.description = lib.getDescription();
        this.url = lib.getUrl();
        this.keywords = lib.getKeywords();
        if (lib.getRepository().getTitle() != this.repository.getTitle()) {
            this.repository = lib.getRepository();
        }
        if (lib.getLicense().getName() != this.license.getName()) {
            this.license = lib.getLicense();
        }
    }
}
