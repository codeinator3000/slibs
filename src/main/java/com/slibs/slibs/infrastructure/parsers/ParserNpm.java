package com.slibs.slibs.infrastructure.parsers;

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
    public List<Library> getLibraries(int page) {
        List<Library> libraries = new ArrayList<>();
        var downloads = 1000000;
        var pageSize = 10;
        var size = (page + 1) * pageSize;

        URI url = URI.create("https://registry.npmjs.org/-/v1/search?text=downloads:%3E=" + downloads + "&size=" + size);
        var req = HttpRequest.newBuilder()
            .uri(url)
            .GET()
            .build();
        var mapper = new ObjectMapper();
        try {
            HttpResponse<String> res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            var json = mapper.readTree(res.body());
            var hits = (ArrayNode) json.get("objects");
            System.out.println("siiiiiiiiize " + hits.size());
            for (int i = hits.size() - pageSize; i < hits.size(); i++) {
                System.out.println("++++" + i);
                var hit = hits.get(i);
                System.out.println("=====(=====" + hit);
                var lib = getLibrary(hit);
                libraries.add(lib);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libraries;
    }

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
        lic.setName(license);
        
        var lib = new Library();
        lib.setTitle(name);
        lib.setDescString(desc);
        lib.setAuthor(author);
        lib.setUrl(url);
        lib.setRepository(repo);
        lib.setLicense(lic);
        return lib;
        
    }
    
}
