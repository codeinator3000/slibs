package com.slibs.slibs.persistence.searchdb.filters;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import com.slibs.slibs.repositories.support.SearchFilter;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;

/**
 * Абстрактный класс фильтра, который наследуется от базового класса фильтра.
 * <br/>
 * Расширяет базовый класс, теперь позволяет добавлять к запросу саму логику фильтрации.
 * <br/>
 * Применимо только в этом слое, конкретно при запросах к поисковой базе данных.
 */
public abstract class SearchDbFilter extends SearchFilter {
    /**
     * Добавляет фильтрацию по полю к основному запросу.
     * @param query основной запрос поиска.
     */
    public abstract void addToQuery(NativeQueryBuilder query);
}
