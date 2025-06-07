package com.slibs.slibs.services.interfaces;

import java.util.List;

import com.slibs.slibs.entities.License;
import com.slibs.slibs.entities.Repository;



public interface IndexService {
    /**
     * Обновляет данные о всех библиотеках в основной и поисковой базах данных.
     * @return true, если обновление прошло успешно
     */
    public Boolean updateIndex();
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
