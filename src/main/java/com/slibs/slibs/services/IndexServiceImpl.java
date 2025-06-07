package com.slibs.slibs.services;

import java.util.List;

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


@Service
public class IndexServiceImpl implements IndexService {
    private final LibSearchRepo libSearchRepo;
    private final LibraryRepo libraryRepo;
    private final ParserManager parserManager;
    private final RepositoryRepo repositoryRepo;
    private final LicenseRepo licenseRepo;

    public IndexServiceImpl(LibSearchRepo libSearchRepo, LibraryRepo libraryRepo, ParserManager parserManager,
            RepositoryRepo repositoryRepo, LicenseRepo licenseRepo) {
        this.libSearchRepo = libSearchRepo;
        this.libraryRepo = libraryRepo;
        this.parserManager = parserManager;
        this.repositoryRepo = repositoryRepo;
        this.licenseRepo = licenseRepo;
    }

    @Override
    public Boolean updateIndex() {
        var maxLibs = 200;
        var repoNames = parserManager.getRepoNames();
        for (var repoName : repoNames) {
            for (int page = 0; page < (maxLibs / 10); page++) {
                try {
                    var libraries = parserManager.getLibraries(repoName, page);
                    for (var lib : libraries) {
                        var foundLibrary = libraryRepo.findByUrl(lib.getUrl());
                        Library resultLib = null;
                        if (foundLibrary == null) {
                            var repo = repositoryRepo.findByTitle(repoName);
                            var lic = licenseRepo.findByName(lib.getLicense().getName());
                            if (repo != null) {
                                lib.setRepository(repo);
                            }
                            if (lic != null) {
                                lib.setLicense(lic);
                            }
                            resultLib = libraryRepo.save(lib);
                        } else {
                            foundLibrary.update(lib);
                            resultLib = libraryRepo.save(foundLibrary);
                        }
                        libSearchRepo.saveOrUpdate(repoName, new LibSearch(resultLib));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

            }
        }
        return true;
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
