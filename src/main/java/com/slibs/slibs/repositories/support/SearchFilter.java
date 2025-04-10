package com.slibs.slibs.repositories.support;

import co.elastic.clients.elasticsearch.license.License;
import lombok.Data;

@Data
public class SearchFilter {
    private License license;
}
