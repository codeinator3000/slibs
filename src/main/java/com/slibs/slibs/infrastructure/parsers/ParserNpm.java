package com.slibs.slibs.infrastructure.parsers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.slibs.slibs.entities.Library;
import com.slibs.slibs.entities.License;
import com.slibs.slibs.entities.Repository;
import com.slibs.slibs.infrastructure.Parser;

@Component
public class ParserNpm implements Parser {
    private final int PAGE_SIZE = 10;
    // Фильтрация по загрузкам
    // (нужно, чтобы при запросе вернулись самые популярные библиотеки)
    private final int DOWNLOADS_FILTER = 0;

    private final String repoName = "npm";
    private final HttpClient httpClient;

    public ParserNpm() {
        this.httpClient = HttpClient.newHttpClient();
    }
    @Override
    public String getRepoName() {
        return repoName;
    }

    @Override
    public List<Library> getLibraries(int page) throws IOException, InterruptedException {
        // Количество библиотек в одном запросе
        var pageSize = 10;

        var req = getHttpRequest(page);

        var mapper = new ObjectMapper();
        HttpResponse<String> res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        var json = mapper.readTree(res.body());
        var hits = (ArrayNode) json.get("objects");
        var libs = getLibrariesFromHits(hits);
        return libs;
    }

    private List<Library> getLibrariesFromHits(ArrayNode hits) {
        List<Library> libraries = new ArrayList<>();

        for (int i = hits.size() - PAGE_SIZE; i < hits.size(); i++) {
            var hit = hits.get(i);
            var lib = getLibrary(hit);
            libraries.add(lib);
        }

        return libraries;
    }

    private HttpRequest getHttpRequest(int page) {
        // Количество библиотек, которые будут загружены
        var size = (page + 1) * PAGE_SIZE;

        URI url = URI.create("https://registry.npmjs.org/-/v1/search?text=downloads:%3E=" + DOWNLOADS_FILTER
                + "&size=" + size);
        var req = HttpRequest.newBuilder()
                .uri(url)
                .GET()
                .build();

        return req;
    }

    /**
     * Парсит библиотеку из JSON, вернувшегося от запроса к NPM хранилищу
     * @param json библиотека в JSON формате
     * @return подробные данные библиотеки
     */
    private Library getLibrary(JsonNode json) {
        var pkg = json.get("package");
        var name = pkg.get("name").asText();
        var desc = pkg.get("description").asText();
        var author = pkg.get("publisher").get("username").asText();
        var url = pkg.get("links").get("npm").asText();
        var license = pkg.get("license").asText();

        var repo = new Repository();
        repo.setTitle(repoName);

        var lic = new License();
        license = license.split("-")[0];
        lic.setName(license);
        
        var lib = new Library();
        lib.setTitle(name);
        lib.setDescription(desc);
        lib.setAuthor(author);
        lib.setUrl(url);
        lib.setRepository(repo);
        lib.setLicense(lic);
        return lib;
        
    }
    
}
