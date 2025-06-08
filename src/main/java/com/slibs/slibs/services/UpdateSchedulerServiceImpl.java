package com.slibs.slibs.services;

import com.slibs.slibs.schedulers.UpdateIndexWorker;
import com.slibs.slibs.services.interfaces.UpdateSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateSchedulerServiceImpl implements UpdateSchedulerService {
    private final UpdateIndexWorker updateIndexWorker;
    @Override
    public Boolean isAutoUpdateEnable() {
        return updateIndexWorker.getActiveStatus();
    }

    @Override
    public Boolean switchAutoUpdate() {
        updateIndexWorker.init();
        if (updateIndexWorker.getActiveStatus()) {
            updateIndexWorker.end();
            return false;
        }
        return updateIndexWorker.start();
    }
}
