package com.slibs.slibs.services.interfaces;

import java.util.List;

import co.elastic.clients.elasticsearch.license.License;
import co.elastic.clients.elasticsearch.snapshot.Repository;

public interface MainDbService {
    /**
     * Возвращает список всех репозиториев, которые доступны в основной базе данных.
     * @return список репозиториев
     */
    public List<Repository> getAllRepositories();
    /**
     * Возвращает список всех лицензий, которые доступны в основной базе данных.
     * @return список лицензий
     */
    public List<License> getAllLicenses();
}
