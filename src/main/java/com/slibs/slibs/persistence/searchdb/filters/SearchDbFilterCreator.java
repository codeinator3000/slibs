package com.slibs.slibs.persistence.searchdb.filters;

import com.slibs.slibs.repositories.support.FilterCreator;
import com.slibs.slibs.repositories.support.SearchFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реализация фабричного метода для elasticsearch
 */
@Component
@Primary
public class SearchDbFilterCreator implements FilterCreator {
    @Override
    public List<SearchFilter> createFilters(Optional<String> license) {
        var filters = new ArrayList<SearchFilter>();
        license.ifPresent(licenseName -> filters
                        .add(new LicenseFilter(licenseName)));

        return filters;
    }
}
