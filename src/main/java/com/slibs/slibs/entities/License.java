package com.slibs.slibs.entities;

import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class License {
    @Id
    private int id;
    private String name;
    @ManyToOne
    private List<Library> libraries;
}
