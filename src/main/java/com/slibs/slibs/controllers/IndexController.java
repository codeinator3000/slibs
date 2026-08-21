package com.slibs.slibs.controllers;

import java.util.List;

import com.slibs.slibs.services.interfaces.UpdateSchedulerService;
import org.springframework.web.bind.annotation.*;

import com.slibs.slibs.entities.License;
import com.slibs.slibs.entities.Repository;
import com.slibs.slibs.services.interfaces.IndexService;

import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/index")
@AllArgsConstructor
public class IndexController {
    private final IndexService indexService;
    private final UpdateSchedulerService updateSchedulerService;

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean updateIndex() {
        return indexService.updateIndex();
    }

    @GetMapping("/repositories")
    public List<Repository> getAllRepositories() {
        return indexService.getAllRepositories();
    }

    @GetMapping("/licenses")
    public List<String> getAllLicense() {
        return indexService.getAllLicenses().stream().map(License::getName).toList();
    }

    /**
     * Переключает состояние автоматического обновления индекса.
     * @return true, если автоматическое обновление включено.
     */
    @PostMapping("/update/auto")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean switchAutoUpdate() {
        return updateSchedulerService.switchAutoUpdate();
    }

    /**
     * Возвращает состояние автоматического обновления индекса.
     * @return true, если автоматическое обновление включено.
     */
    @GetMapping("/update/auto")
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean isAutoUpdateEnabled() {
        return updateSchedulerService.isAutoUpdateEnable();
    }
    
}
