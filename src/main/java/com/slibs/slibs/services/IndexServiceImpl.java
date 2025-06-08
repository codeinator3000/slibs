package com.slibs.slibs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.slibs.slibs.entities.LibSearch;
import com.slibs.slibs.entities.Library;
import com.slibs.slibs.entities.License;
import com.slibs.slibs.entities.Repository;
import com.slibs.slibs.infrastructure.ParserManager;
import com.slibs.slibs.repositories.LibSearchRepo;
import com.slibs.slibs.repositories.LibraryRepo;
import com.slibs.slibs.repositories.LicenseRepo;
import com.slibs.slibs.repositories.RepositoryRepo;
import com.slibs.slibs.services.interfaces.IndexService;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class IndexServiceImpl implements IndexService {
    private final LibSearchRepo libSearchRepo;
    private final LibraryRepo libraryRepo;
    private final ParserManager parserManager;
    private final RepositoryRepo repositoryRepo;
    private final LicenseRepo licenseRepo;

    @Value("${libs.max}")
    int maxLibs;
    @Value("${libs.page-size}")
    int pageSize;

    @Override
    @Transactional
    public Boolean updateIndex() {
        var repoNames = parserManager.getRepoNames();
        try {
            // Для каждого репозитория
            for (var repoName : repoNames) {
                // По страницам
                for (int page = 0; page < (int)Math.ceil((double)maxLibs / pageSize); page++) {
                    // Получаем все библиотеки из репозитория за эту страницу
                    var libraries = parserManager.getLibraries(repoName, page, pageSize);
                    for (var lib : libraries) {
                        // Обновление или добавление данных в основную базу данных
                        var resultLib = saveOrUpdateLibData(repoName, lib);

                        // Обновление или добавление данных в поисковую базу данных
                        libSearchRepo.saveOrUpdate(repoName, new LibSearch(resultLib));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Сохраняет или обновляет данные в основной базе данных
     * @param repoName название репозитория, в котором находятся библиотеки
     * @param currentLibrary полученные данные библиотеки (новая библиотека или данные уже существующей)
     * @return обновленные данные библиотеки
     */
    private Library saveOrUpdateLibData(String repoName, Library currentLibrary) {
        // Устанавливаем реальные зависимые данные, если они есть
        var repos = repositoryRepo.findByTitle(repoName);
        var lics = licenseRepo.findByName(currentLibrary.getLicense().getName());
        if (!repos.isEmpty()) {
            currentLibrary.setRepository(repos.getFirst());
        }
        if (!lics.isEmpty()) {
            currentLibrary.setLicense(lics.getFirst());
        }

        // Нет ли такой библиотеки в индексе?
        var foundedLibraries = libraryRepo.findByUrl(currentLibrary.getUrl());
        if (!foundedLibraries.isEmpty()) {
            var foundedLib = foundedLibraries.getFirst();
            foundedLib.update(currentLibrary);
            return libraryRepo.save(foundedLib);
        }
        return libraryRepo.save(currentLibrary);
    }

    @Override
    public List<Repository> getAllRepositories() {
        return repositoryRepo.findAll();
    }

    @Override
    public List<License> getAllLicenses() {
        return licenseRepo.findAll();
    }
}
