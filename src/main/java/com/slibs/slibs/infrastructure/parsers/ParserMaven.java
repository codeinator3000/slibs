package com.slibs.slibs.infrastructure.parsers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.slibs.slibs.entities.Library;
import com.slibs.slibs.entities.License;
import com.slibs.slibs.entities.Repository;
import com.slibs.slibs.infrastructure.Parser;

@Component
public class ParserMaven implements Parser {
    private final String repoName = "maven";
    private final HttpClient httpClient;
    
    public ParserMaven() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public String getRepoName() {
        return repoName;
    }

    @Override
    public List<Library> getLibraries(int page) {
        List<Library> libs = new ArrayList<>();
        URI url = URI.create("http://127.0.0.1:8000/maven/" + (page + 1));
        var req = HttpRequest.newBuilder()
            .uri(url)
            .GET()
            .build();
        var mapper = new ObjectMapper();
        try {
            HttpResponse<String> res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            var arrJson = mapper.readTree(res.body());
            for (JsonNode jsonNode : arrJson) {
                var lib = getLibrary(jsonNode);
                libs.add(lib);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return libs;
    }
    
    private Library getLibrary(JsonNode json) {
        var name = json.get("title").asText();
        var desc = json.get("description").asText();
        var url = json.get("url").asText();
        var license = json.get("license").asText();
        var repo = new Repository();
        repo.setTitle(repoName);
        var lic = new License();
        lic.setName(license);
        
        var lib = new Library();
        lib.setTitle(name);
        lib.setDescription(desc);
        lib.setAuthor(null);
        lib.setUrl(url);
        lib.setRepository(repo);
        lib.setLicense(lic);
        return lib;
    }
}
