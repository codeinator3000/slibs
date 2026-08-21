package com.slibs.slibs.services.interfaces;

import java.util.List;

import com.slibs.slibs.entities.Library;
import com.slibs.slibs.repositories.support.SearchFilter;
import com.slibs.slibs.services.support.LibraryResponse;

public interface SearchService {
    /**
     * Выполняет поиск аналогов библиотеки по её названию.
     * Поиск производится по всем репозиториям, указанным в списке.
     * @param repositoryName название репозитория, в котором находится указанная библиотека
     * @param libName название библиотеки
     * @param repositories список репозиториев, в которых производится поиск
     * @param page номер страницы результата поиска
     * @return список аналогов библиотеки
     */
    public List<Library> search(String repositoryName, String libName,
                                   List<String> repositories, int page);
    /**
     * Выполняет поиск аналогов библиотеки по её названию, 
     * учитывая лицензию.
     * @param repositoryName название репозитория, в котором находится указанная библиотека
     * @param libName название библиотеки
     * @param repositories список репозиториев, в которых производится поиск
     * @param page номер страницы результата поиска
     * @param filters фильтры для поиска
     * @return список аналогов библиотеки
     */
    public List<Library> search(String repositoryName, String libName,
        List<String> repositories, int page, List<SearchFilter> filters);
}
