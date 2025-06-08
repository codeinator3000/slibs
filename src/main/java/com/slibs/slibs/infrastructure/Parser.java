package com.slibs.slibs.infrastructure;

import java.util.List;

import com.slibs.slibs.entities.Library;

import reactor.core.publisher.Mono;

/**
 * Интерфейс для парсеров. Они возвращают список подробных данных библиотек.
 */
public interface Parser {
    /**
     * Возвращает название репозитория
     * @return название репозитория
     */
    public String getRepoName();
    /**
     * Парсит список данных библиотек из JSON и возвращает его в виде списка объектов библиотек
     * @param page номер страницы. Начинается с 1.
     * @param pageSize количество библиотек на странице. Максимум 100.
     * @return список объектов библиотек
     */
    public List<Library> getLibraries(int page, int pageSize);
}
