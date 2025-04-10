package com.slibs.slibs.repositories;

import java.util.List;

import com.slibs.slibs.entities.LibSearch;
import com.slibs.slibs.repositories.support.SearchFilter;

public interface LibSearchRepo {
    /**
     * Сохраняет или обновляет данные для поиска библиотеки в индексе.
     * @param indexName - название индекса, в котором находится библиотека. Обычно, это название репозитория.
     * @param libSearch - данные библиотеки для добавления или обновления.
     */
    void saveOrUpdate(String indexName, LibSearch libSearch);
    /**
     * Удаляет данные библиотеки из индекса.
     * @param indexName - название индекса, в котором находится библиотека. Обычно, это название репозитория.
     * @param id - идентификатор библиотеки. 
     */
    void delete(String indexName, int id);
    /**
     * Удаляет индекс со всеми данными.
     * @param indexName - название индекса. Обычно, это название репозитория.
     */
    void deleteIndex(String indexName);
    /**
     * Находит библиотеку в индексе по ее описанию.
     * @param indexName - название индекса, в котором находится библиотека. Обычно, это название репозитория.
     * @param description - описание библиотеки. по которому необходимо провести поиск.
     * @return данные найденной библиотеки.
     */
    LibSearch findByDescription(String indexName, String description);
    /**
     * Находит библиотеки в индексе по ее описанию.
     * @param indexName - название индекса, в котором находится библиотека. Обычно, это название репозитория.
     * @param description - описание библиотеки. по которому необходимо провести поиск.
     * @param page - номер страницы результата поиска.
     * @return данные всех найденных библиотек.
     */
    List<LibSearch> findAllByDescription(String indexName, String description, int page);
    /**
     * Находит библиотеки в индексе по ее описанию.
     * @param indexName - название индекса, в котором находится библиотека. Обычно, это название репозитория.
     * @param description - описание библиотеки. по которому необходимо провести поиск.
     * @param page - номер страницы результата поиска.
     * @param filter - фильтр для поиска
     * @return данные всех найденных библиотек.
     */
    List<LibSearch> findAllByDescription(String indexName, String description, int page, SearchFilter filter);
}
