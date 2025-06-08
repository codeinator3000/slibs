package com.slibs.slibs.infrastructure.support;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;

import com.slibs.slibs.entities.Library;
import com.slibs.slibs.entities.License;
import com.slibs.slibs.entities.Repository;

@Component
public class LibraryMapper {
    public Library toLibrary(LibraryDto libraryDto) {
        if (libraryDto == null) {
            return null;
        }

        var title = libraryDto.getTitle();
        var description = libraryDto.getDescription();
        var keywords = libraryDto.getKeywords();
        var url = libraryDto.getUrl();
        String repository = libraryDto.getRepository();
        var license = libraryDto.getLicense();
        String lang = libraryDto.getLanguage();

        var lib = new Library();
        var repo = new Repository();
        var lic = new License();

        lic.setName(license);
        
        repo.setTitle(repository.toLowerCase());
        if (lang == null) {
            lang = "different";
        }
        repo.setLang(lang.toLowerCase());

        lib.setTitle(title);
        lib.setDescription(description);
        lib.setKeywords(String.join(" ", keywords));
        lib.setUrl(url);
        lib.setRepository(repo);
        lib.setLicense(lic);
        
        return lib;
    }
    public List<Library> toLibraryList(List<LibraryDto> libraryDtos) {
        if (libraryDtos == null || libraryDtos.isEmpty()) {
            return new ArrayList<>();
        }
        var libs = new ArrayList<Library>();
        for (LibraryDto libraryDto : libraryDtos) {
            libs.add(toLibrary(libraryDto));
        }

        return libs;
    }
}
