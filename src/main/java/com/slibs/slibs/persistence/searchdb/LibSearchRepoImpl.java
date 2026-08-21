package com.slibs.slibs.persistence.searchdb;

import java.util.List;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;

import com.slibs.slibs.persistence.searchdb.filters.SearchDbFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Repository;

import com.slibs.slibs.entities.LibSearch;
import com.slibs.slibs.repositories.LibSearchRepo;
import com.slibs.slibs.repositories.support.SearchFilter;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
@Repository
public class LibSearchRepoImpl implements LibSearchRepo {
    private final ElasticsearchOperations esOps; 

    public LibSearchRepoImpl(ElasticsearchOperations esOps) {
        this.esOps = esOps;
        
    }

    @Override
    public void saveOrUpdate(String indexName, LibSearch libSearch) {
        esOps.save(libSearch, IndexCoordinates.of(indexName));
        esOps.indexOps(IndexCoordinates.of(indexName)).refresh();
    }

    @Override
    public Boolean delete(String indexName, int id) {
        var libId = esOps.delete(id, IndexCoordinates.of(indexName));
        return libId != null;
    }

    @Override
    public Boolean deleteIndex(String indexName) {
        return esOps.indexOps(IndexCoordinates.of(indexName)).delete();
    }

    @Override
    public LibSearch findByDescription(String indexName, String description) {
        var query = new NativeQueryBuilder()
                .withQuery(q -> q.match(m -> m.field("description").query(description)))
                .withPageable(PageRequest.of(0, 1)).build();

        var searchHits = esOps.search(query, LibSearch.class, IndexCoordinates.of(indexName));

        var lib = searchHits.getSearchHits().stream()
                .findFirst()
                .map(SearchHit::getContent)
                .orElse(null);
        return lib;
    }

    @Override
    public List<LibSearch> findAllByDescription(String indexName, String description, int page) {
        return findAllByDescription(indexName, description, page, null);
    }

    @Override
    public List<LibSearch> findAllByDescription(String indexName, String description,
                                                int page, List<SearchFilter> filters) {
        var queryBuilder = new NativeQueryBuilder()
                .withQuery(q -> q.match(m -> m.field("description").query(description)));
        if (!filters.isEmpty()) {
            filters.forEach(f -> ((SearchDbFilter)f).addToQuery(queryBuilder));
        }
        var query = queryBuilder.withPageable(PageRequest.of(page, 10)).build();

        var searchHits = esOps.search(query, LibSearch.class, IndexCoordinates.of(indexName));

        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
    }
    
}
