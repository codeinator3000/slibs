package com.slibs.slibs.entities;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class LibSearch {
    @Id
    private int id;
    @Field(name = "main_id")
    private int mainId;
    private String description;
    private String license;
}
