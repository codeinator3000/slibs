package com.slibs.slibs.schedulers;

import com.slibs.slibs.services.interfaces.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateIndexWorker extends BackgroundPlanWorker {
    private final IndexService indexService;

    @Value("${index.update.auto.cron}")
    String cronString;

    @Override
    protected CronTrigger cron() {
        return new CronTrigger(cronString);
    }

    @Override
    @Transactional
    protected void function() {
        indexService.updateIndex();
    }
}
