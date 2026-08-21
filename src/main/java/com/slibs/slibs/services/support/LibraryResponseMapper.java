package com.slibs.slibs.services.support;

import java.util.ArrayList;
import java.util.List;

import com.slibs.slibs.entities.Library;
import org.springframework.stereotype.Component;

@Component
public class LibraryResponseMapper {
    public LibraryResponse toLibraryResponse(Library library) {
        if (library == null) {
            return null;
        }

        var title = library.getTitle();
        var description = library.getDescription();
        var url = library.getUrl();
        var repository = library.getRepository().getTitle();
        var license = library.getLicense().getName();

        return new LibraryResponse(title, description, url, repository, license);
    }

    public List<LibraryResponse> toLibraryResponseList(List<Library> libraries) {
        var libs = new ArrayList<LibraryResponse>();

        for (var lib : libraries) {
            libs.add(toLibraryResponse(lib));
        }
        return libs;
    }
}
