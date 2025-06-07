package com.slibs.slibs.persistence.searchdb.filters;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import com.slibs.slibs.repositories.support.SearchFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.query.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;

/**
 * Фильтр по лицензии.
 */
@Getter
@AllArgsConstructor
public class LicenseFilter extends SearchDbFilter {
    private String license;

    @Override
    public void addToQuery(NativeQueryBuilder query) {
        query = query
                .withFilter(f -> f
                        .term(t -> t
                                .field("license.keyword")
                                .value(license)
                        )
                );
    }
}
