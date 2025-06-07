package com.slibs.slibs.services.interfaces;

import java.util.List;

import co.elastic.clients.elasticsearch.license.License;
import co.elastic.clients.elasticsearch.snapshot.Repository;

public interface UpdateSchedulerService {
    /**
     * Возвращает состояние автоматического обновления.
     * @return true, если автоматическое обновление включено
     */
    public Boolean isAutoUpdateEnable();
    /**
     * Переключает состояние автоматического обновления.
     * @return true, если автоматическое обновление включено
     */
    public Boolean switchAutoUpdate();
}
